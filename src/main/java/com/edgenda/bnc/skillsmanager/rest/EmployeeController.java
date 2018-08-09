package com.edgenda.bnc.skillsmanager.rest;

import com.edgenda.bnc.skillsmanager.model.Employee;
import com.edgenda.bnc.skillsmanager.rest.entity.EmployeeResource;
import com.edgenda.bnc.skillsmanager.rest.entity.SkillResource;
import com.edgenda.bnc.skillsmanager.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "Get employee by ID")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public EmployeeResource getEmployee(@PathVariable Long id) {
        final EmployeeResource employeeResource = EmployeeResource.fromEmployee(employeeService.getEmployee(id));
        return addEmployeeResourceLinks(id, employeeResource);
    }

    static EmployeeResource addEmployeeResourceLinks(Long employeeId, EmployeeResource employeeResource) {
        employeeResource.add(
                linkTo(methodOn(EmployeeController.class).getEmployee(employeeId)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).getEmployeeSkills(employeeId)).withRel("skills")
        );
        return employeeResource;
    }

    @ApiOperation(value = "Get all employees")
    @RequestMapping(method = RequestMethod.GET)
    public Resources<EmployeeResource> getEmployees() {
        final Link self = linkTo(methodOn(EmployeeController.class).getEmployees()).withSelfRel();
        final List<EmployeeResource> employeesResources = employeeService.getEmployees()
                .stream()
                .map(employee -> addEmployeeResourceLinks(employee.getId(), EmployeeResource.fromEmployee(employee)))
                .collect(toList());
        return new Resources<>(employeesResources, self);
    }

    @ApiOperation(value = "Create an employee")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResource createEmployee(EmployeeResource employeeResource) {
        final Employee savedEmployee = employeeService.createEmployee(employeeResource.toEmployee());
        return addEmployeeResourceLinks(
                savedEmployee.getId(),
                EmployeeResource.fromEmployee(savedEmployee)
        );
    }

    @ApiOperation(value = "Update an employee")
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable Long id, EmployeeResource employeeResource) {
        employeeService.updateEmployee(employeeResource.toEmployee().toBuilder().id(id).build());
    }

    @ApiOperation(value = "Get employee's skills")
    @RequestMapping(path = "/{id}/skills", method = RequestMethod.GET)
    public Resources<SkillResource> getEmployeeSkills(@PathVariable Long id) {
        final Link self = linkTo(methodOn(EmployeeController.class).getEmployeeSkills(id)).withSelfRel();
        final List<SkillResource> skillResources = employeeService.getEmployeeSkills(id)
                .stream()
                .map(skill -> {
                    final SkillResource skillResource = SkillResource.fromSkill(skill);
                    return SkillController.addSkillResourceLinks(skill.getId(), skillResource);
                })
                .collect(toList());
        return new Resources<>(skillResources, self);
    }

}

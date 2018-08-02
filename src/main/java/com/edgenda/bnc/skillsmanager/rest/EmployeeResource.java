package com.edgenda.bnc.skillsmanager.rest;

import com.edgenda.bnc.skillsmanager.model.Employee;
import com.edgenda.bnc.skillsmanager.model.Skill;
import com.edgenda.bnc.skillsmanager.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Get employee by ID")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @ApiOperation(value = "Get all employees")
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @ApiOperation(value = "Create an employee")
    @RequestMapping(method = RequestMethod.POST)
    public Employee createEmployee(Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @ApiOperation(value = "Update an employee")
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateEmployee(@PathVariable Long id, Employee employee) {
        final Employee updatedEmployee = Employee.builder()
                .id(id)
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
        employeeService.updateEmployee(updatedEmployee);
    }

    @ApiOperation(value = "Get employee's skills")
    @RequestMapping(path = "/{id}/skills", method = RequestMethod.GET)
    public List<Skill> getEmployeeSkills(@PathVariable Long id) {
        return employeeService.getEmployeeSkills(id);
    }

}

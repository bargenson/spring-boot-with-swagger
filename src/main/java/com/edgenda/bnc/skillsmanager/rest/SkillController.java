package com.edgenda.bnc.skillsmanager.rest;

import com.edgenda.bnc.skillsmanager.model.Skill;
import com.edgenda.bnc.skillsmanager.rest.entity.EmployeeResource;
import com.edgenda.bnc.skillsmanager.rest.entity.SkillResource;
import com.edgenda.bnc.skillsmanager.service.SkillService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @ApiOperation(value = "Get a skill by ID")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public SkillResource getSkill(@PathVariable Long id) {
        final Skill skill = skillService.getSkill(id);
        final SkillResource skillResource = SkillResource.fromSkill(skill);
        addSkillResourceLinks(skill.getId(), skillResource);
        return skillResource;
    }

    static SkillResource addSkillResourceLinks(Long skillId, SkillResource skillResource) {
        skillResource.add(
                linkTo(methodOn(SkillController.class).getSkill(skillId)).withSelfRel(),
                linkTo(methodOn(SkillController.class).getEmployeesWithSkill(skillId)).withRel("employees")
        );
        return skillResource;
    }

    @ApiOperation(value = "Get all skills")
    @RequestMapping(method = RequestMethod.GET)
    public List<SkillResource> getSkills() {
        return skillService.getSkills()
                .stream()
                .map(skill -> {
                    final SkillResource skillResource = SkillResource.fromSkill(skill);
                    addSkillResourceLinks(skill.getId(), skillResource);
                    return skillResource;
                })
                .collect(toList());
    }

    @ApiOperation(value = "Get employees with skill")
    @RequestMapping(path = "/{id}/employees", method = RequestMethod.GET)
    public List<EmployeeResource> getEmployeesWithSkill(@PathVariable Long id) {
        return skillService.getEmployeesWithSkill(id)
                .stream()
                .map(employee -> EmployeeController.addEmployeeResourceLinks(
                        employee.getId(),
                        EmployeeResource.fromEmployee(employee))
                )
                .collect(toList());
    }

}

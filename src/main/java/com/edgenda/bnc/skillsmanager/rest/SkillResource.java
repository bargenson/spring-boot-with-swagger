package com.edgenda.bnc.skillsmanager.rest;

import com.edgenda.bnc.skillsmanager.model.Employee;
import com.edgenda.bnc.skillsmanager.model.Skill;
import com.edgenda.bnc.skillsmanager.service.SkillService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillResource {

    @Autowired
    private SkillService skillService;

    @ApiOperation(value = "Get a skill by ID")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Skill getSkill(@PathVariable Long id) {
        return skillService.getSkill(id);
    }

    @ApiOperation(value = "Get all skills")
    @RequestMapping(method = RequestMethod.GET)
    public List<Skill> getSkills() {
        return skillService.getSkills();
    }

    @ApiOperation(value = "Get employees with skill")
    @RequestMapping(path = "/{id}/employees", method = RequestMethod.GET)
    public List<Employee> getEmployeesWithSkill(@PathVariable Long id) {
        return skillService.getEmployeesWithSkill(id);
    }

}

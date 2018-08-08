package com.edgenda.bnc.skillsmanager.service;

import com.edgenda.bnc.skillsmanager.model.Employee;
import com.edgenda.bnc.skillsmanager.model.Skill;
import com.edgenda.bnc.skillsmanager.repository.EmployeeRepository;
import com.edgenda.bnc.skillsmanager.repository.SkillRepository;
import com.edgenda.bnc.skillsmanager.service.exception.UnknownEmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final SkillRepository skillRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, SkillRepository skillRepository) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    public Employee getEmployee(Long id) {
        Assert.notNull(id, "Employee ID cannot be null");
        return employeeRepository.findById(id)
                .orElseThrow(() -> new UnknownEmployeeException(id));
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        Assert.notNull(employee, "Employee cannot be null");
        final Employee newEmployee = employee.toBuilder().id(null).build();
        return employeeRepository.save(newEmployee);
    }

    public void updateEmployee(Employee employee) {
        Assert.notNull(employee, "Employee cannot be null");
        this.getEmployee(employee.getId());
        employeeRepository.save(employee);
    }

    public List<Skill> getEmployeeSkills(Long employeeId) {
        return skillRepository.findByEmployeeId(employeeId);
    }
}

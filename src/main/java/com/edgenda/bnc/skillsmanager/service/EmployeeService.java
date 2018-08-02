package com.edgenda.bnc.skillsmanager.service;

import com.edgenda.bnc.skillsmanager.model.Employee;
import com.edgenda.bnc.skillsmanager.model.Skill;
import com.edgenda.bnc.skillsmanager.repository.EmployeeRepository;
import com.edgenda.bnc.skillsmanager.repository.SkillRepository;
import com.edgenda.bnc.skillsmanager.service.exception.UnknownEmployeeException;
import com.edgenda.bnc.skillsmanager.service.exception.UnknownSkillException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

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
        final Employee newEmployee = Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
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

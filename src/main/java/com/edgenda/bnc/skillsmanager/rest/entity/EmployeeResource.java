package com.edgenda.bnc.skillsmanager.rest.entity;

import com.edgenda.bnc.skillsmanager.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Builder
@AllArgsConstructor
public class EmployeeResource extends ResourceSupport {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    public static EmployeeResource fromEmployee(Employee employee) {
        return new EmployeeResource(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public Employee toEmployee() {
        return Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }

}

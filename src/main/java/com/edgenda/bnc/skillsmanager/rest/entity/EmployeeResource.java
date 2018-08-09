package com.edgenda.bnc.skillsmanager.rest.entity;

import com.edgenda.bnc.skillsmanager.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeResource extends ResourceSupport {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    public EmployeeResource(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

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

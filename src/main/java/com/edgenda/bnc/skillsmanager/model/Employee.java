package com.edgenda.bnc.skillsmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnoreProperties({ "employees" })
    private List<Skill> skills;

}

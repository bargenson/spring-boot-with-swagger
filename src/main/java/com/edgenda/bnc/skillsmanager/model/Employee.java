package com.edgenda.bnc.skillsmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
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
    private List<Skill> skills;

    @PreRemove
    private void removeSkillsFromEmployee() {
        for (Skill skill : skills) {
            skill.getEmployees().remove(this);
        }
    }

}

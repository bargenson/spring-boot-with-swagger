package com.edgenda.bnc.skillsmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Skill {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @ManyToMany
    @JoinTable(name = "EMPLOYEES_SKILLS")
    @JsonIgnoreProperties({ "skills" })
    private List<Employee> employees;

}

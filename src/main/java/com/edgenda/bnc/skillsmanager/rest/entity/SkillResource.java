package com.edgenda.bnc.skillsmanager.rest.entity;

import com.edgenda.bnc.skillsmanager.model.Skill;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillResource extends ResourceSupport {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    public SkillResource(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description
    ) {
        this.name = name;
        this.description = description;
    }

    public static SkillResource fromSkill(Skill skill) {
        return new SkillResource(
                skill.getName(),
                skill.getDescription()
        );
    }

}

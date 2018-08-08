package com.edgenda.bnc.skillsmanager.rest.entity;

import com.edgenda.bnc.skillsmanager.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Builder
@AllArgsConstructor
public class SkillResource extends ResourceSupport {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    public static SkillResource fromSkill(Skill skill) {
        return new SkillResource(
                skill.getName(),
                skill.getDescription()
        );
    }

}

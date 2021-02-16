package pl.radomczak.repository;

import pl.radomczak.model.Skill;

import java.util.Collection;
import java.util.Optional;

public class SkillsRepository {
    private final Collection<Skill> skills;

    public SkillsRepository(Collection<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill)
    {
        skills.add(skill);
    }

    public Collection<Skill> getSkills() {
        return skills;
    }

    public Optional<Skill> findByName(String name) {
        for (Skill skill : skills) {
            if (skill.getName().equals(name))
                return Optional.of(skill);
        }
        return Optional.empty();
    }
}

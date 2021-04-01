package pl.radomczak.model;

import java.util.Objects;
import java.util.Set;

public class Skill extends Item implements CSVConvertible {
    private Race race;
    private Set<Skill> requiredSkills;

    public Skill(String name, String description, String image, Set<Ability> requiredAbilities, Race race, Set<Skill> requiredSkills) {
        super(name, description, image, requiredAbilities);
        this.race = race;
        this.requiredSkills = requiredSkills;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Skill skill = (Skill) o;
        return race == skill.race && Objects.equals(requiredSkills, skill.requiredSkills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), race, requiredSkills);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "race=" + race +
                ", requiredSkills=" + requiredSkills +
                '}';
    }

    public static SkillBuilder builder() {
        return new SkillBuilder();
    }

    @Override
    public String toCSV() {
        StringBuilder builder = new StringBuilder();
        int index;
        //Name
        builder.append(getName());
        builder.append(";");
        //Description
        builder.append(getDescription());
        builder.append(";");
        //Image path
        builder.append(getImage());
        builder.append(";");
        //Abilities
        for (Ability ability : getRequiredAbilities()) {
            builder.append(ability.getName());
            builder.append(",");
        }
        if (builder.charAt(builder.length()-1) == ',') {
            index = (builder.length()-1);
            builder.deleteCharAt(index);
        }
        builder.append(";");
        //Race
        builder.append(race);
        builder.append(";");
        //Skills
        for (Skill skill : requiredSkills) {
            builder.append(skill.getName());
            builder.append(",");
        }
        if (builder.charAt(builder.length()-1) == ',') {
            index = (builder.length()-1);
            builder.deleteCharAt(index);
        }
        builder.append(";");

        return builder.toString();
    }

    public static final class SkillBuilder {
        private String name;
        private String description;
        private String image;
        private Set<Ability> requiredAbilities;
        private Race race;
        private Set<Skill> requiredSkills;

        private SkillBuilder() {
        }

        public SkillBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SkillBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SkillBuilder withImage(String image) {
            this.image = image;
            return this;
        }

        public SkillBuilder withRequiredAbilities(Set<Ability> requiredAbilities) {
            this.requiredAbilities = requiredAbilities;
            return this;
        }

        public SkillBuilder withRace(Race race) {
            this.race = race;
            return this;
        }

        public SkillBuilder withRequiredSkills(Set<Skill> requiredSkills) {
            this.requiredSkills = requiredSkills;
            return this;
        }

        public Skill build() {
            return new Skill(name, description, image, requiredAbilities, race, requiredSkills);
        }
    }
}

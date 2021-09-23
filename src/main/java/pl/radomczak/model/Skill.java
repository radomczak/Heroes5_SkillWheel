package pl.radomczak.model;

import java.util.Objects;
import java.util.Set;

public class Skill extends Item implements CSVConvertible {
    private Set<Skill> requiredSkills;
    private Ability parentAbility;  //First required ability is a parentAbility wich makes this skill dependent on it

    public Skill(String name, String description, String image, Set<Ability> requiredAbilities, Set<Race> allowedRaces, Set<Skill> requiredSkills) {
        super(name, description, image, requiredAbilities, allowedRaces);
        this.requiredSkills = requiredSkills;
        this.parentAbility = (Ability) requiredAbilities.toArray()[0];
    }

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Ability getParentAbility() {
        return parentAbility;
    }

    public void setParentAbility(Ability parentAbility) {
        this.parentAbility = parentAbility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Skill skill = (Skill) o;
        return requiredSkills.equals(skill.requiredSkills) && parentAbility.equals(skill.parentAbility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), requiredSkills, parentAbility);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "requiredSkills=" + requiredSkills +
                ", parentAbility=" + parentAbility +
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
        for (Race race : getAllowedRaces()) {
            builder.append(race.name());
            builder.append(",");
        }
        if (builder.charAt(builder.length()-1) == ',') {
            index = (builder.length()-1);
            builder.deleteCharAt(index);
        }
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
        private Set<Race> allowedRaces;  //powinno być allowedRaces jako set
        private Set<Skill> requiredSkills;
        private Ability parentAbility;  //First required ability is a parentAbility wich makes this skill dependent on it

        private SkillBuilder() {
        }

        public static SkillBuilder aSkill() {
            return new SkillBuilder();
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

        public SkillBuilder withAllowedRaces(Set<Race> allowedRaces) {
            this.allowedRaces = allowedRaces;
            return this;
        }

        public SkillBuilder withRequiredSkills(Set<Skill> requiredSkills) {
            this.requiredSkills = requiredSkills;
            return this;
        }

        public SkillBuilder withParentAbility(Ability parentAbility) {
            this.parentAbility = parentAbility;
            return this;
        }

        public Skill build() {
            Skill skill = new Skill(name, description, image, requiredAbilities, allowedRaces, requiredSkills);
            skill.setParentAbility(parentAbility);
            return skill;
        }
    }
}

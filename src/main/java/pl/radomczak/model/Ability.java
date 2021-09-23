package pl.radomczak.model;

import java.util.Objects;
import java.util.Set;

public class Ability extends Item implements CSVConvertible {
    private int proficiencyLevel;
    private boolean racial;

    public Ability(String name, String description, String image, int proficiencyLevel, boolean racial,Set<Race> allowedRaces ,Set<Ability> requiredAbilities) {
        super(name, description, image, requiredAbilities, allowedRaces); //requiredAbilities only for advanced abilities that require basic ones.
        this.proficiencyLevel = proficiencyLevel;
        this.racial = racial;
    }

    public int getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(int proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public boolean isRacial() {
        return racial;
    }

    public void setRacial(boolean racial) {
        this.racial = racial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ability ability = (Ability) o;
        return proficiencyLevel == ability.proficiencyLevel && racial == ability.racial;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), proficiencyLevel, racial);
    }

    @Override
    public String toString() {
        String isRacial = "";
        if (racial)
        {
            isRacial += ("| " + getAllowedRaces());
        }
        return "Umiejętność " + getName() +  "| Ścieżka do obrazu - '" + getImage() + "'| Poziom mistrzostwa = " + proficiencyLevel + isRacial + "\n" + getDescription() +"\n";
    }

    public static AbilityBuilder builder() {
        return new AbilityBuilder();
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
        //Proficiency level
        builder.append(proficiencyLevel);
        builder.append(";");
        //Racial
        builder.append(racial);
        builder.append(";");
        //Races
        for (Race race : getAllowedRaces()) {
            builder.append(race);
            builder.append(",");
        }
        if (builder.charAt(builder.length()-1) == ',') {
            index = (builder.length()-1);
            builder.deleteCharAt(index);
        }
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

        return builder.toString();
    }


    public static final class AbilityBuilder {
        private int proficiencyLevel;
        private boolean racial;
        private String name;
        private String description;
        private String image;
        private Set<Ability> requiredAbilities;
        private Set<Race> allowedRaces;

        private AbilityBuilder() {
        }

        public static AbilityBuilder anAbility() {
            return new AbilityBuilder();
        }

        public AbilityBuilder withProficiencyLevel(int proficiencyLevel) {
            this.proficiencyLevel = proficiencyLevel;
            return this;
        }

        public AbilityBuilder withRacial(boolean racial) {
            this.racial = racial;
            return this;
        }

        public AbilityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AbilityBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public AbilityBuilder withImage(String image) {
            this.image = image;
            return this;
        }

        public AbilityBuilder withRequiredAbilities(Set<Ability> requiredAbilities) {
            this.requiredAbilities = requiredAbilities;
            return this;
        }

        public AbilityBuilder withAllowedRaces(Set<Race> allowedRaces) {
            this.allowedRaces = allowedRaces;
            return this;
        }

        public Ability build() {
            return new Ability(name, description, image, proficiencyLevel, racial, allowedRaces, requiredAbilities);
        }
    }
}


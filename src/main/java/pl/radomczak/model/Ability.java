package pl.radomczak.model;

import java.util.Objects;
import java.util.Set;

public class Ability extends Item {
    private int proficiencyLevel;
    private boolean racial;
    private final Set<Race> allowedRaces;

    public Ability(String name, String description, String image, int proficiencyLevel, boolean racial,Set<Race> allowedRaces ,Set<Ability> requiredAbilities) {
        super(name, description, image, requiredAbilities); //requiredAbilities only for advanced abilities that require basic ones.
        this.proficiencyLevel = proficiencyLevel;
        this.racial = racial;
        this.allowedRaces = allowedRaces;
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

    public Set<Race> getAllowedRaces() {
        return allowedRaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ability ability = (Ability) o;
        return proficiencyLevel == ability.proficiencyLevel && racial == ability.racial && allowedRaces.equals(ability.allowedRaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), proficiencyLevel, racial, allowedRaces);
    }

    @Override
    public String toString() {
        String isRacial = "";
        if (racial)
        {
            isRacial += ("| " + allowedRaces);
        }
        return "Umiejętność " + getName() +  "| Ścieżka do obrazu - '" + getImage() + "'| Poziom mistrzostwa = " + proficiencyLevel + isRacial + "\n" + getDescription() +"\n";
    }

    public static AbilityBuilder builder() {
        return new AbilityBuilder();
    }

    public static final class AbilityBuilder {
        private int proficiencyLevel;
        private boolean racial;
        private Set<Race> allowedRaces;
        private String name;
        private String description;
        private String image;
        private Set<Ability> requiredAbilities;

        private AbilityBuilder() {
        }

        public AbilityBuilder withProficiencyLevel(int proficiencyLevel) {
            this.proficiencyLevel = proficiencyLevel;
            return this;
        }

        public AbilityBuilder withRacial(boolean racial) {
            this.racial = racial;
            return this;
        }

        public AbilityBuilder withAllowedRaces(Set<Race> allowedRaces) {
            this.allowedRaces = allowedRaces;
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

        public Ability build() {
            return new Ability(name, description, image, proficiencyLevel, racial, allowedRaces, requiredAbilities);
        }
    }
}


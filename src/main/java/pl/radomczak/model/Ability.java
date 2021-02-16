package pl.radomczak.model;

import java.util.Objects;
import java.util.Set;

public class Ability extends Item {
    private int proficiencyLevel;
    private Race racial;
    private Set<Race> allowedRaces;

    public Ability(String name, String description, String image, int proficiencyLevel, Race racial,Set<Race> allowedRaces ,Set<Ability> requiredAbilities) {
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

    public Race getRacial() {
        return racial;
    }

    public void setRacial(Race racial) {
        this.racial = racial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ability ability = (Ability) o;
        return proficiencyLevel == ability.proficiencyLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), proficiencyLevel);
    }

    @Override
    public String toString() {
        String isRacial = "";
        if (racial != null)
        {
            isRacial += ("| " + racial);
        }
        return "Umiejętność " + getName() +  "| Ścieżka do obrazu - '" + getImage() + "'| Poziom mistrzostwa = " + proficiencyLevel + isRacial + "\n" + getDescription() +"\n";
    }
}


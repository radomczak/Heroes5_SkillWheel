package pl.radomczak.model;

import java.util.Objects;
import java.util.Set;

public class Hero {
    private String name;
    private Skill uniqueSkill;
    private Set<Skill> startingSkills;
    private Set<Ability> startingAbilities;
    private Race race;

    public Hero(String name, Skill uniqueSkill, Set<Skill> startingSkills, Set<Ability> startingAbilities, Race race) {
        this.name = name;
        this.uniqueSkill = uniqueSkill;
        this.startingSkills = startingSkills;
        this.startingAbilities = startingAbilities;
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Skill getUniqueSkill() {
        return uniqueSkill;
    }

    public void setUniqueSkill(Skill uniqueSkill) {
        this.uniqueSkill = uniqueSkill;
    }

    public Set<Skill> getStartingSkills() {
        return startingSkills;
    }

    public void setStartingSkills(Set<Skill> startingSkills) {
        this.startingSkills = startingSkills;
    }

    public Set<Ability> getStartingAbilities() {
        return startingAbilities;
    }

    public void setStartingAbilities(Set<Ability> startingAbilities) {
        this.startingAbilities = startingAbilities;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return name.equals(hero.name) && uniqueSkill.equals(hero.uniqueSkill) && startingSkills.equals(hero.startingSkills) && startingAbilities.equals(hero.startingAbilities) && race == hero.race;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uniqueSkill, startingSkills, startingAbilities, race);
    }

    public static HeroBuilder builder() {
        return new HeroBuilder();
    }

    public static final class HeroBuilder {
        private String name;
        private Skill uniqueSkill;
        private Set<Skill> startingSkills;
        private Set<Ability> startingAbilities;
        private Race race;

        private HeroBuilder() {
        }

        public HeroBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public HeroBuilder withUniqueSkill(Skill uniqueSkill) {
            this.uniqueSkill = uniqueSkill;
            return this;
        }

        public HeroBuilder withStartingSkills(Set<Skill> startingSkills) {
            this.startingSkills = startingSkills;
            return this;
        }

        public HeroBuilder withStartingAbilities(Set<Ability> startingAbilities) {
            this.startingAbilities = startingAbilities;
            return this;
        }

        public HeroBuilder withRace(Race race) {
            this.race = race;
            return this;
        }

        public Hero build() {
            return new Hero(name, uniqueSkill, startingSkills, startingAbilities, race);
        }
    }
}

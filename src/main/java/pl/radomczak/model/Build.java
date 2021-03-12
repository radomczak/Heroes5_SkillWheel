package pl.radomczak.model;

import java.util.Objects;
import java.util.Set;

public class Build {
    private Hero hero;
    private Set<Skill> skills;
    private Set<Ability> abilities;

    public Build(Hero hero, Set<Skill> skills, Set<Ability> abilities) {
        this.hero = hero;
        this.skills = skills;
        this.abilities = abilities;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Build build = (Build) o;
        return hero.equals(build.hero) && skills.equals(build.skills) && abilities.equals(build.abilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hero, skills, abilities);
    }

    public static BuildBuilder builder() {
        return new BuildBuilder();
    }

    public static final class BuildBuilder {
        private Hero hero;
        private Set<Skill> skills;
        private Set<Ability> abilities;

        private BuildBuilder() {
        }

        public BuildBuilder withHero(Hero hero) {
            this.hero = hero;
            return this;
        }

        public BuildBuilder withSkills(Set<Skill> skills) {
            this.skills = skills;
            return this;
        }

        public BuildBuilder withAbilities(Set<Ability> abilities) {
            this.abilities = abilities;
            return this;
        }

        public Build build() {
            return new Build(hero, skills, abilities);
        }
    }
}

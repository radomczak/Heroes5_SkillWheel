package pl.radomczak.model;

import java.util.Objects;
import java.util.Set;

public class Build implements CSVConvertible {
    private String name;
    private Hero hero;
    private Set<Skill> skills;
    private Set<Ability> abilities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return name.equals(build.name) && hero.equals(build.hero) && skills.equals(build.skills) && abilities.equals(build.abilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hero, skills, abilities);
    }

    @Override
    public String toString() {
        return "Build{" +
                "name='" + name + '\'' +
                ", hero=" + hero +
                ", skills=" + skills +
                ", abilities=" + abilities +
                '}';
    }

    public static BuildBuilder builder() {
        return new BuildBuilder();
    }

    @Override
    public String toCSV() {
        StringBuilder builder = new StringBuilder();
        int index;
        //Name
        builder.append(getName());
        builder.append(";");
        //Hero
        builder.append(hero.getName());
        builder.append(";");
        //Skills
        for (Skill skill : skills) {
            builder.append(skill.getName());
            builder.append(",");
        }
        if (builder.charAt(builder.length()-1) == ',') {
            index = (builder.length()-1);
            builder.deleteCharAt(index);
        }
        builder.append(";");
        //Abilities
        for (Ability ability : abilities) {
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

    public static final class BuildBuilder {
        private String name;
        private Hero hero;
        private Set<Skill> skills;
        private Set<Ability> abilities;

        private BuildBuilder() {
        }

        public BuildBuilder withName(String name) {
            this.name = name;
            return this;
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
            Build build = new Build();
            build.setName(name);
            build.setHero(hero);
            build.setSkills(skills);
            build.setAbilities(abilities);
            return build;
        }
    }
}

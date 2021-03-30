package pl.radomczak.model;

import java.util.LinkedHashSet;

public class Wheel {
    private final LinkedHashSet<Ability> abilities = new LinkedHashSet<>();
    private final LinkedHashSet<Skill> skills = new LinkedHashSet<>();
    private final LinkedHashSet<ItemPosition> itemPositions = new LinkedHashSet<>();
    private final LinkedHashSet<Hero> heroes = new LinkedHashSet<>();
    private final LinkedHashSet<Build> builds = new LinkedHashSet<>();

    private Hero activeHero;
    private LinkedHashSet<Item> activeItems;
    private LinkedHashSet<Item> allowedItems;

    public LinkedHashSet<Ability> getAbilities() {
        return abilities;
    }

    public LinkedHashSet<Skill> getSkills() {
        return skills;
    }

    public LinkedHashSet<ItemPosition> getItemPositions() {
        return itemPositions;
    }

    public LinkedHashSet<Hero> getHeroes() {
        return heroes;
    }

    public LinkedHashSet<Build> getBuilds() {
        return builds;
    }

    public Hero getActiveHero() {
        return activeHero;
    }

    public LinkedHashSet<Item> getActiveItems() {
        return activeItems;
    }

    public LinkedHashSet<Item> getAllowedItems() {
        return allowedItems;
    }

    public void setActiveHero(Hero activeHero) {
        this.activeHero = activeHero;
    }

    public void setActiveItems(LinkedHashSet<Item> activeItems) {
        this.activeItems = activeItems;
    }

    public void setAllowedItems(LinkedHashSet<Item> allowedItems) {
        this.allowedItems = allowedItems;
    }
}

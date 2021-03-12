package pl.radomczak.model;

import java.util.HashMap;
import java.util.HashSet;

public class Wheel {
    private final HashSet<Ability> abilities = new HashSet<>();
    private final HashSet<Skill> skills = new HashSet<>();
    private final HashSet<ItemPosition> itemPositions = new HashSet<>();
    private final HashSet<Hero> heroes = new HashSet<>();
    private final HashMap<String, Build> builds = new HashMap<>();

    private Hero activeHero;
    private HashSet<Item> activeItems;
    private HashSet<Item> allowedItems;

    public HashSet<Ability> getAbilities() {
        return abilities;
    }

    public HashSet<Skill> getSkills() {
        return skills;
    }

    public HashSet<ItemPosition> getItemPositions() { return itemPositions; }

    public HashSet<Hero> getHeroes() {
        return heroes;
    }

    public HashMap<String, Build> getBuilds() {
        return builds;
    }

    public Hero getActiveHero() {
        return activeHero;
    }

    public void setActiveHero(Hero activeHero) {
        this.activeHero = activeHero;
    }

    public HashSet<Item> getActiveItems() {
        return activeItems;
    }

    public void setActiveItems(HashSet<Item> activeItems) {
        this.activeItems = activeItems;
    }

    public HashSet<Item> getAllowedItems() {
        return allowedItems;
    }

    public void setAllowedItems(HashSet<Item> allowedItems) {
        this.allowedItems = allowedItems;
    }
}

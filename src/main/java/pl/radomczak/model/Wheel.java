package pl.radomczak.model;

import pl.radomczak.repositories.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Wheel
{
    private final HashSet<Ability> abilities = new HashSet<>();
    private final HashSet<Skill> skills = new HashSet<>();
    private final HashSet<ItemPosition> itemPositions = new HashSet<>();
    private final HashSet<Hero> heroes = new HashSet<>();
    private final HashMap<String, Build> builds = new HashMap<>();

    private Hero activeHero;
    private Set<Item> activeItems;
    private Set<Item> allowedItems;

    public HashSet<Ability> getAbilities() {
        return abilities;
    }

    public HashSet<Skill> getSkills() {
        return skills;
    }

    public HashSet<ItemPosition> getItemPositions() {
        return itemPositions;
    }

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

    public Set<Item> getActiveItems() {
        return activeItems;
    }

    public void setActiveItems(Set<Item> activeItems) {
        this.activeItems = activeItems;
    }

    public Set<Item> getAllowedItems() {
        return allowedItems;
    }

    public void setAllowedItems(Set<Item> allowedItems) {
        this.allowedItems = allowedItems;
    }
}

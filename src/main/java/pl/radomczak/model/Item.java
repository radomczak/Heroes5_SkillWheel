package pl.radomczak.model;

import java.util.Objects;
import java.util.Set;

public class Item {
    private String name;
    private String description;
    private String image;
    private Set<Ability> requiredAbilities;
    private final Set<Race> allowedRaces;

    public Item(String name, String description, String image, Set<Ability> requiredAbilities, Set<Race> allowedRaces) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.requiredAbilities = requiredAbilities;
        this.allowedRaces = allowedRaces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Ability> getRequiredAbilities() {
        return requiredAbilities;
    }

    public void setRequiredAbilities(Set<Ability> requiredAbilities) {
        this.requiredAbilities = requiredAbilities;
    }

    public Set<Race> getAllowedRaces() {
        return allowedRaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name) && description.equals(item.description) && image.equals(item.image) && requiredAbilities.equals(item.requiredAbilities) && allowedRaces.equals(item.allowedRaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, image, requiredAbilities, allowedRaces);
    }
}




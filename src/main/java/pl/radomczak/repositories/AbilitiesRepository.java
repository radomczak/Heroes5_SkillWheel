package pl.radomczak.repositories;

import pl.radomczak.model.Ability;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class AbilitiesRepository
{
    private final Collection<Ability> abilities;

    public AbilitiesRepository(Collection<Ability> abilities) {
        this.abilities = abilities;
    }

    public void addAbility(Ability ability)
    {
        abilities.add(ability);
    }

    public Collection<Ability> getAbilities()
    {
        return abilities;
    }

    public Optional<Ability> findByName(String name)
    {
        for (Ability ability : abilities)
        {
            if (ability.getName().equals(name))
                return Optional.of(ability);
        }
        return Optional.empty();
    }
}

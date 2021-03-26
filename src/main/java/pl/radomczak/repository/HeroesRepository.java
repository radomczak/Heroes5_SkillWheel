package pl.radomczak.repository;

import pl.radomczak.model.Hero;

import java.util.Collection;
import java.util.Optional;

public class HeroesRepository {
    private final Collection<Hero> heroes;

    public HeroesRepository(Collection<Hero> heroes) {
        this.heroes = heroes;
    }

    public void addHero(Hero hero)
    {
        heroes.add(hero);
    }

    public Collection<Hero> getHeroes() {
        return heroes;
    }

    public Optional<Hero> findByName(String name) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(name))
                return Optional.of(hero);
        }
        return Optional.empty();
    }
}

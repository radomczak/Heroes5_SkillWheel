package pl.radomczak.repository;

import pl.radomczak.model.Hero;

import java.util.Collection;

public class HeroesRepository {
    private final Collection<Hero> heroes;

    public HeroesRepository(Collection<Hero> heroes) {
        this.heroes = heroes;
    }

    public void addHero(Hero hero)
    {
        heroes.add(hero);
    }
}

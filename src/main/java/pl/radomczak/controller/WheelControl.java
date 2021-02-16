package pl.radomczak.controller;

import pl.radomczak.model.*;
import pl.radomczak.repository.*;

public class WheelControl {
    private Wheel wheel;
    private AbilitiesRepository abilitiesRepository;
    private BuildsRepository buildsRepository;
    private HeroesRepository heroesRepository;
    private PositionRepository positionRepository;
    private SkillsRepository skillsRepository;

    public WheelControl() {
        if (!importWheel()) {
            this.wheel = new Wheel();
            this.abilitiesRepository = new AbilitiesRepository(wheel.getAbilities());
            this.buildsRepository = new BuildsRepository(wheel.getBuilds());
            this.heroesRepository = new HeroesRepository(wheel.getHeroes());
            this.positionRepository = new PositionRepository(wheel.getItemPositions());
            this.skillsRepository = new SkillsRepository(wheel.getSkills());
        }
    }

    private boolean importWheel()
    {
        return false;
    }

    public void addSkill(Skill skill)
    {
        skillsRepository.addSkill(skill);
    }

    public void addAbility(Ability ability)
    {
        abilitiesRepository.addAbility(ability);
    }
    public void addHero(Hero hero)
    {
        heroesRepository.addHero(hero);
    }
    public void addBuild(String name,Build build)
    {
        buildsRepository.addBuild(name,build);
    }

    public Wheel getWheel() {
        return wheel;
    }

    public AbilitiesRepository getAbilitiesRepository() {
        return abilitiesRepository;
    }

    public BuildsRepository getBuildsRepository() {
        return buildsRepository;
    }

    public HeroesRepository getHeroesRepository() {
        return heroesRepository;
    }

    public PositionRepository getPositionRepository() {
        return positionRepository;
    }

    public SkillsRepository getSkillsRepository() {
        return skillsRepository;
    }
}

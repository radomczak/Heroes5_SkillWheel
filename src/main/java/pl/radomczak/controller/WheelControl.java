package pl.radomczak.controller;

import pl.radomczak.controller.io.file.CSVFileManager;
import pl.radomczak.controller.io.file.FileManager;
import pl.radomczak.model.*;
import pl.radomczak.model.exception.DataImportException;
import pl.radomczak.repository.*;

public class WheelControl {
    private Wheel wheel;
    private final AbilitiesRepository abilitiesRepository;
    private final BuildsRepository buildsRepository;
    private final HeroesRepository heroesRepository;
    private final PositionRepository positionRepository;
    private final SkillsRepository skillsRepository;

    public WheelControl() {
        if (!importWheel()) {
            this.wheel = new Wheel();
        }
            this.abilitiesRepository = new AbilitiesRepository(wheel.getAbilities());
            this.buildsRepository = new BuildsRepository(wheel.getBuilds());
            this.heroesRepository = new HeroesRepository(wheel.getHeroes());
            this.positionRepository = new PositionRepository(wheel.getItemPositions());
            this.skillsRepository = new SkillsRepository(wheel.getSkills());
    }

    private boolean importWheel() {
        FileManager fileManager = new CSVFileManager();
        try {
            wheel =  fileManager.importWheel();
            return true;
        } catch (DataImportException e) {
            System.out.println(e.getMessage());
            System.out.println("Inicjuje nowa baze");
        }
        return false;
    }

    public void addSkill(Skill skill) {
        skillsRepository.addSkill(skill);
    }

    public void addAbility(Ability ability) {
        abilitiesRepository.addAbility(ability);
    }

    public void addHero(Hero hero) {
        heroesRepository.addHero(hero);
    }

    public void addBuild(String name,Build build) {
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

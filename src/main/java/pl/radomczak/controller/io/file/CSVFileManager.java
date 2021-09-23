package pl.radomczak.controller.io.file;

import pl.radomczak.model.*;
import pl.radomczak.model.exception.AbilityNotFoundException;
import pl.radomczak.model.exception.DataImportException;
import pl.radomczak.model.exception.NoSuchRaceException;
import pl.radomczak.model.exception.SkillNotFoundException;
import pl.radomczak.repository.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;

public class CSVFileManager implements FileManager {

    private AbilitiesRepository abilitiesRepository;
    private BuildsRepository buildsRepository;
    private HeroesRepository heroesRepository;
    private SkillsRepository skillsRepository;

    @Override
    public Wheel importWheel() {
        Wheel wheel = new Wheel();
        getRepositories(wheel);
        importAbilities();
        importSkills();
        importHeroes();
        importBuilds();
        return wheel;
    }

    @Override
    public void exportWheel(Wheel wheel) {

    }

    private void getRepositories(Wheel wheel) {
        this.abilitiesRepository = new AbilitiesRepository(wheel.getAbilities());
        this.buildsRepository = new BuildsRepository(wheel.getBuilds());
        this.heroesRepository = new HeroesRepository(wheel.getHeroes());
        this.skillsRepository = new SkillsRepository(wheel.getSkills());
    }

    private void importAbilities() {
        String FILE_NAME = "Abilities.csv";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createAbilityFromString)
                    .forEach(abilitiesRepository::addAbility);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        }
    }

    private Ability createAbilityFromString(String csvText) {
        String[] data = csvText.split(";",-1);
        Ability ability;

        String name = data[0];
        String description = data[1];
        String image = data[2];
        int proficiencyLevel = Integer.parseInt(data[3]);
        boolean racial = Boolean.parseBoolean(data[4]);
        HashSet<Race> races = getRacesFromString(data[5]);

        HashSet<Ability> abilities;
        try {
            abilities = getAbilitiesFromString(data[6]);
        } catch (AbilityNotFoundException e) {
            throw new DataImportException(e.getMessage());
        }

        ability = Ability.builder()
                .withName(name)
                .withDescription(description)
                .withImage(image)
                .withProficiencyLevel(proficiencyLevel)
                .withAllowedRaces(races)
                .withRacial(racial)
                .withRequiredAbilities(abilities)
                .build();
        return ability;
    }

    private void importSkills(){
        String FILE_NAME = "Skills.csv";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createSkillFromString)
                    .forEach(skillsRepository::addSkill);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        }
    }

    private Skill createSkillFromString(String csvText) {
        String[] data = csvText.split(";",-1);
        Skill skill;

        String name = data[0];
        String description = data[1];
        String image = data[2];
        HashSet<Race> races = getRacesFromString(data[4]);

        HashSet<Ability> requiredAbilities;
        try {
            requiredAbilities = getAbilitiesFromString(data[3]);
        } catch (AbilityNotFoundException e) {
            throw new DataImportException(e.getMessage());
        }

        HashSet<Skill> skills;
        try {
            skills = getSkillsFromString(data[5]);
        } catch (SkillNotFoundException e) {
            throw new DataImportException(e.getMessage());
        }

        skill = Skill.builder()
                .withName(name)
                .withDescription(description)
                .withImage(image)
                .withRequiredAbilities(requiredAbilities)
                .withAllowedRaces(races)
                .withRequiredSkills(skills)
                .build();
        return skill;
    }

    private void importHeroes() {
        String FILE_NAME = "Heroes.csv";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createHeroFromString)
                    .forEach(heroesRepository::addHero);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        }
    }

    private Hero createHeroFromString(String csvText) {
        String[] data = csvText.split(";",-1);
        Hero hero;

        String name = data[0];

        Skill uniqueSkill = null;
        Optional<Skill> s = skillsRepository.findByName(data[1]);
        if (s.isPresent()) uniqueSkill = s.get();

        HashSet<Skill> startingSkills;
        HashSet<Ability> startingAbilities;
        try {
            startingSkills = getSkillsFromString(data[2]);
            startingAbilities = getAbilitiesFromString(data[3]);
        } catch (SkillNotFoundException | AbilityNotFoundException e) {
            throw new DataImportException(e.getMessage());
        }

        Race race;
        try {
            race = Race.createOptionFromString(data[4]);
        } catch (NoSuchRaceException ex) {
            throw new DataImportException(ex.getMessage());
        }

        hero = Hero.builder()
                .withName(name)
                .withUniqueSkill(uniqueSkill)
                .withStartingSkills(startingSkills)
                .withStartingAbilities(startingAbilities)
                .withRace(race)
                .build();
        return hero;
    }

    private void importBuilds() {
        String FILE_NAME = "Builds.csv";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createBuildFromString)
                    .forEach(buildsRepository::addBuild);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        }
    }

    private Build createBuildFromString(String csvText) {
        String[] data = csvText.split(";",-1);
        Build build;

        String name = data[0];

        Hero hero = null;
        Optional<Hero> h = heroesRepository.findByName(data[1]);
        if (h.isPresent()) hero = h.get();

        HashSet<Skill> skillSet;
        HashSet<Ability> abilitySet;
        try {
            skillSet = getSkillsFromString(data[2]);
            abilitySet = getAbilitiesFromString(data[3]);
        } catch (SkillNotFoundException | AbilityNotFoundException e) {
            throw new DataImportException(e.getMessage());
        }

        build = Build.builder()
                .withName(name)
                .withHero(hero)
                .withSkills(skillSet)
                .withAbilities(abilitySet)
                .build();

        return build;
    }

    private HashSet<Ability> getAbilitiesFromString(String abilitiesString) throws AbilityNotFoundException {
        HashSet<Ability> abilities = new HashSet<>();
        if (!abilitiesString.equals("")) {
            String[] data = abilitiesString.split(",");
            Optional<Ability> ability;
            for (String s : data) {
                ability = abilitiesRepository.findByName(s);
                if (ability.isPresent()) abilities.add(ability.get());
                else throw new AbilityNotFoundException("Nie znaleziono umiejetnosci: " + s);
            }
        }
        return abilities;
    }

    private HashSet<Skill> getSkillsFromString(String skillsString) throws SkillNotFoundException {
        HashSet<Skill> skills = new HashSet<>();
        if (!skillsString.equals("")) {
            String[] data = skillsString.split(",");
            Optional<Skill> skill;
            for (String s : data) {
                skill = skillsRepository.findByName(s);
                if (skill.isPresent()) skills.add(skill.get());
                else throw new SkillNotFoundException("Nie znaleziono umiejetnosci: " + s);
            }
        }
        return skills;
    }

    private HashSet<Race> getRacesFromString(String raceString) {
        HashSet<Race> races = new HashSet<>();
        if (!raceString.equals("")) {
            String[] data = raceString.split(",");
            Race race;
            for (String s : data) {
                try {
                    race = Race.createOptionFromString(s);
                    races.add(race);
                } catch (NoSuchRaceException ex) {
                    throw new DataImportException(ex.getMessage());
                }
            }
        }
        return races;
    }

}

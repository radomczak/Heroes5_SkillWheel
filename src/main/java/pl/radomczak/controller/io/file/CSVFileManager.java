package pl.radomczak.controller.io.file;

import pl.radomczak.model.*;
import pl.radomczak.model.exception.DataImportException;
import pl.radomczak.model.exception.NoSuchRaceException;
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
    public Wheel importWheel() throws DataImportException {
        Wheel wheel = new Wheel();
        getRepositories(wheel);

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

    private void importSkills() {
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
        String[] data = csvText.split(";");
        Skill skill;

        String name = data[0];
        String description = data[1];
        String image = data[2];
        HashSet<Ability> requiredAbilities = getAbilitiesFromString(data[3]);
        Race race;
        try {
            race = Race.createOptionFromString(data[4]);
        } catch (NoSuchRaceException ex) {
            throw new DataImportException(ex.getMessage());
        }
        HashSet<Skill> skills = getSkillsFromString(data[5]);

        skill = Skill.builder()
                .withName(name)
                .withDescription(description)
                .withImage(image)
                .withRequiredAbilities(requiredAbilities)
                .withRace(race)
                .withRequiredSkills(skills)
                .build();
        return skill;
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
        String[] data = csvText.split(";");
        Ability ability;

        String name = data[0];
        String description = data[1];
        String image = data[2];
        int proficiencyLevel = Integer.parseInt(data[3]);
        boolean racial = Boolean.parseBoolean(data[4]);
        HashSet<Race> races = getRacesFromString(data[5]);
        HashSet<Ability> abilities = getAbilitiesFromString(data[6]);

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
        String[] data = csvText.split(";");
        Hero hero;

        String name = data[0];

        Skill uniqueSkill = null;
        Optional<Skill> s = skillsRepository.findByName(data[1]);
        if (s.isPresent()) uniqueSkill = s.get();

        HashSet<Skill> startingSkills = getSkillsFromString(data[2]);
        HashSet<Ability> startingAbilities = getAbilitiesFromString(data[3]);
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
        String[] data = csvText.split(";");
        Build build;

        String name = data[0];
        Hero hero = null;
        Optional<Hero> h = heroesRepository.findByName(data[1]);
        if (h.isPresent()) hero = h.get();
        HashSet<Skill> skillSet = getSkillsFromString(data[2]);
        HashSet<Ability> abilitySet = getAbilitiesFromString(data[3]);

        build = Build.builder()
                .withName(name)
                .withHero(hero)
                .withSkills(skillSet)
                .withAbilities(abilitySet)
                .build();

        return build;
    }

    private HashSet<Ability> getAbilitiesFromString(String abilitiesString) {
        if (abilitiesString.equals(""))
            return null;
        else {
            String[] data = abilitiesString.split(",");
            HashSet<Ability> abilities = new HashSet<>();
            Optional<Ability> ability;
            for (String s : data) {
                ability = abilitiesRepository.findByName(s);
                ability.ifPresent(abilities::add);
            }
            return abilities;
        }
    }

    private HashSet<Skill> getSkillsFromString(String skillsString) {
        if (skillsString.equals(""))
            return null;
        else {
            String[] data = skillsString.split(",");
            HashSet<Skill> skills = new HashSet<>();
            Optional<Skill> skill;
            for (String s : data) {
                skill = skillsRepository.findByName(s);
                skill.ifPresent(skills::add);
            }
            return skills;
        }
    }

    private HashSet<Race> getRacesFromString(String raceString) {
        if (raceString.equals(""))
            return null;
        else {
            String[] data = raceString.split(",");
            HashSet<Race> races = new HashSet<>();
            Race race;
            for (String s : data) {
                try {
                    race = Race.createOptionFromString(s);
                    races.add(race);
                } catch (NoSuchRaceException ex) {
                    throw new DataImportException(ex.getMessage());
                }
            }
            return races;
        }
    }

}

package pl.radomczak.controller.io.file;

import pl.radomczak.model.Ability;
import pl.radomczak.model.Race;
import pl.radomczak.model.Skill;
import pl.radomczak.model.Wheel;
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

    private void importSkills(Wheel wheel) {
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

}

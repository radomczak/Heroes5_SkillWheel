package pl.radomczak.controller.io.file;

import pl.radomczak.model.Skill;
import pl.radomczak.model.Wheel;
import pl.radomczak.model.exception.DataImportException;
import pl.radomczak.repository.SkillsRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVFileManager implements FileManager {

    @Override
    public Wheel importWheel() throws DataImportException {
        Wheel wheel = new Wheel();
        return wheel;
    }

    @Override
    public void exportWheel(Wheel wheel) {

    }

    private void importSkills(Wheel wheel) {
        String FILE_NAME = "Skills.csv";
        SkillsRepository skills = new SkillsRepository(wheel.getSkills());
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createSkillFromString)
                    .forEach(skills::addSkill);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        }
    }

    private Skill createSkillFromString(String csvText) {
        /*
        String[] data = csvText.split(";");
        Skill skill;

        String name;
        String description;
        String image;
        Set<Ability> requiredAbilities;
        Race race;
        Set<Skill> skills;

        skill = Skill.builder()
                .withName(name)
                .withDescription(description)
                .withImage(image)
                .withRequiredAbilities(requiredAbilities)
                .withRace(race)
                .withRequiredSkills(skills)
                .build();
        return skill;
         */
        return null;
    }
}

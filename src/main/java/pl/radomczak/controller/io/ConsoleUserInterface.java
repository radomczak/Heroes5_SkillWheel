package pl.radomczak.controller.io;

import pl.radomczak.controller.WheelControl;
import pl.radomczak.model.*;
import pl.radomczak.model.exception.NoSuchOptionException;

import java.util.InputMismatchException;
import java.util.HashSet;

public class ConsoleUserInterface implements UserInterface {
    private WheelControl wheelControl;
    private final ConsoleReader reader;
    private final ConsolePrinter printer;

    public ConsoleUserInterface() {
        this.reader = new ConsoleReader();
        this.printer = new ConsolePrinter();
    }

    @Override
    public void handle() {
        Option option;
        do {
            printer.printOptions();
            option = getOption();

            switch (option) {
                case EXIT: {
                    close();
                    break;
                }
                case ADDSKILL: {
                    addSkill();
                    break;
                }
                case PRINTSKILLS: {
                    printSkills();
                    break;
                }
                case ADDABILITY: {
                    addAbility();
                    break;
                }
                case PRINTABILITIES: {
                    printAbilities();
                    break;
                }
                case ADDHERO: {
                    addHero();
                    break;
                }
                case PRINTHEROES: {
                    printHeroes();
                    break;
                }
                case ADDBUILD: {
                    addBuild();
                    break;
                }
                case PRINTBUILDS: {
                    printBuilds();
                    break;
                }
            }
        }while(option != Option.EXIT);
    }

    private void addSkill() {
        if (wheelControl.getAbilitiesRepository().getAbilities().isEmpty()) {
            printer.print("Najpierw dodaj umiejętności, bo zdolności wymagają przynajmniej jednej umiejętności");
        } else {
            Skill skill;
            String name = reader.readSimpleStringField("Nazwa","Niewłaściwa nazwa, spróbuj ponownie");
            String description = reader.readSimpleStringField("Opis","Niewłaściwy opis, spróbuj ponownie");
            String image = reader.readSimpleStringField("Scieżka do obrazu","Niewłaściwa sciezka, spróbuj ponownie");
            Race race = reader.readRace("Wybierz rase","Niewłaściwa rasa, spróbuj ponownie");
            HashSet<Skill> skills = null;

            boolean needsSkills = reader.readSimpleBooleanField("Czy wymaga innych zdolności? (True / False)","Niewłaściwa odpowiedź spróbuj ponownie");
            if (needsSkills) {
                skills = reader.readSetOfSkills("Wymagane zdolności: (Format: zdolność1,zdolność2,zdolność3 itp. lub brak)","Niewłaściwy format danych, spróbuj jeszcze raz", wheelControl.getSkillsRepository());
            }

            HashSet<Ability> abilities = reader.readSetOfAbilities("Wymagane umiejętności: (Format: umiejetność1,umiejetność2,umiejetność3 itp.)","Niewłaściwy format danych, spróbuj jeszcze raz", wheelControl.getAbilitiesRepository());

            skill = Skill.builder()
                .withName(name)
                .withDescription(description)
                .withImage(image)
                .withRequiredAbilities(abilities)
                .withRace(race)
                .withRequiredSkills(skills)
                .build();
            wheelControl.addSkill(skill);
        }
    }

    private void printSkills() {
        printer.print("Zdolności:");
        for (Skill skill : wheelControl.getSkillsRepository().getSkills()) {
            printer.print(skill);
        }
    }

    private void addAbility() {
        Ability ability;
        String name = reader.readSimpleStringField("Nazwa","Niewłaściwa nazwa, spróbuj ponownie");
        String description = reader.readSimpleStringField("Opis","Niewłaściwy opis, spróbuj ponownie");
        String image = reader.readSimpleStringField("Scieżka do obrazu","Niewłaściwa sciezka, spróbuj ponownie");
        int proficiencyLevel = reader.readConditionIntField("Poziom mistrzostwa (1 - 4)","Niewłaściwy poziom mistrzostwa, spróbuj ponownie",x -> x <= 4 && x >= 1);
        HashSet<Race> races = reader.readSetOfRaces("Dla jakich ras jest dostepna? (Format: rasa1,rasa2,rasa3 itp.)");
        boolean racial = false;
        HashSet<Ability> abilities = null;

        if (races.size()==1)
            racial = reader.readSimpleBooleanField("Umiejętność rasowa? (True / False)","Niewłaściwa odpowiedź spróbuj ponownie");

        boolean needsAbilities = reader.readSimpleBooleanField("Wymaga innych umiejętności? (True / False)","Niewłaściwa odpowiedź, spróbuj ponownie");
        if (needsAbilities)
            abilities = reader.readSetOfAbilities("Wymagane umiejętności: (Format: umiejetność1,umiejetność2,umiejetność3 itp.)","Niewłaściwy format danych, spróbuj jeszcze raz", wheelControl.getAbilitiesRepository());

        ability = Ability.builder()
            .withName(name)
            .withDescription(description)
            .withImage(image)
            .withProficiencyLevel(proficiencyLevel)
            .withAllowedRaces(races)
            .withRacial(racial)
            .withRequiredAbilities(abilities)
            .build();
        wheelControl.addAbility(ability);
    }

    private void printAbilities() {
        printer.print("Umiejętności:");
        for (Ability ability : wheelControl.getAbilitiesRepository().getAbilities()) {
            printer.print(ability);
        }
    }

    private void addHero() {
        Hero hero;
        String name = reader.readSimpleStringField("Nazwa","Niewłaściwa nazwa, spróbuj ponownie");
        Skill uniqueSkill = reader.readSkill("Zdolność specjalna","Niewłaściwy format danych, spróbuj jeszcze raz", wheelControl.getSkillsRepository());
        HashSet<Skill> startingSkills = reader.readSetOfSkills("Początkowe zdolności: (Format: zdolność1,zdolność2,zdolność3 itp. lub brak)","Niewłaściwy format danych, spróbuj jeszcze raz", wheelControl.getSkillsRepository());
        HashSet<Ability> startingAbilities = reader.readSetOfAbilities("Początkowe umiejętności: (Format: umiejetność1,umiejetność2,umiejetność3 itp.)","Niewłaściwy format danych, spróbuj jeszcze raz", wheelControl.getAbilitiesRepository());
        Race race = reader.readRace("Wybierz rase","Niewłaściwa rasa, spróbuj ponownie");

        hero = Hero.builder()
                .withName(name)
                .withUniqueSkill(uniqueSkill)
                .withStartingSkills(startingSkills)
                .withStartingAbilities(startingAbilities)
                .withRace(race)
                .build();
        wheelControl.addHero(hero);
    }

    private void printHeroes() {
        printer.print("Bohaterzy:");
        for (Hero hero : wheelControl.getHeroesRepository().getHeroes()) {
            printer.print(hero);
        }
    }

    private void addBuild() {
        Build build;
        String name = reader.readSimpleStringField("Nazwa","Niewłaściwa nazwa, spróbuj ponownie");
        Hero hero = reader.readHero("Bohater","Niewłaściwy format danych, spróbuj jeszcze raz", wheelControl.getHeroesRepository());
        HashSet<Skill> skillSet = reader.readSetOfSkills("Zestaw zdolności: (Format: zdolność1,zdolność2,zdolność3 itp. lub brak)","Niewłaściwy format danych, spróbuj jeszcze raz", wheelControl.getSkillsRepository());
        HashSet<Ability> abilitySet = reader.readSetOfAbilities("Zestaw umiejętności: (Format: umiejetność1,umiejetność2,umiejetność3 itp.)","Niewłaściwy format danych, spróbuj jeszcze raz", wheelControl.getAbilitiesRepository());

        build = Build.builder()
                .withName(name)
                .withHero(hero)
                .withSkills(skillSet)
                .withAbilities(abilitySet)
                .build();

        wheelControl.addBuild(build);
    }

    private void printBuilds() {
        printer.print("Buildy:");
        for (Build build : wheelControl.getBuildsRepository().getBuilds()) {
            printer.print(build);
        }
    }

    private Option getOption() {
        Option option = null;
        boolean optionOk = false;
        do{
            try {
                option = Option.createOptionFromInt(reader.readInt());
                optionOk = true;
            }catch (InputMismatchException e) {
                printer.print("Wprowadzono wartość, która nie jest liczbą, podaj ponownie");
            }catch (NoSuchOptionException ex) {
                printer.print(ex.getMessage() + ", wprowadz ponownie");
            }
        }while (!optionOk);
        return option;
    }

    public void changeReaderInputToString(String stream){
        reader.changeInputStreamToString(stream);
    }

    @Override
    public void close() {
        reader.close();
    }

    @Override
    public void applyFor(WheelControl wheelControl) {
        this.wheelControl = wheelControl;
    }
}

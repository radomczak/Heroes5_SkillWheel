package pl.radomczak.controller.io;

import pl.radomczak.model.*;
import pl.radomczak.model.exception.NoSuchRaceException;
import pl.radomczak.model.exception.SkillNotFoundException;
import pl.radomczak.repository.AbilitiesRepository;
import pl.radomczak.repository.SkillsRepository;

import java.util.*;
import java.util.function.Predicate;

public class ConsoleReader implements Reader {
    private final Scanner input = new Scanner(System.in);

    public int readInt() {
        try {
            return input.nextInt();
        } finally {
            input.nextLine();
        }
    }

    private boolean readBoolean() {
        try {
            return input.nextBoolean();
        } finally {
            input.nextLine();
        }
    }

    private String readString() {
        return input.nextLine();
    }

    public void close()
    {
        input.close();
    }

    public String readSimpleStringField(String initMessage, String errorMessage) {
        String var = null;
        do {
            System.out.println(initMessage);
            try {
                var = readString();
            }catch (InputMismatchException e) {
                System.out.println(errorMessage);
            }
        }while (var == null);
        return var;
    }

    public boolean readSimpleBooleanField(String initMessage, String errorMessage) {
        boolean conditionRacial = false;
        boolean bool = false;
        do {
            System.out.println(initMessage);
            try {
                bool = readBoolean();
                conditionRacial = true;
            }catch (InputMismatchException e) {
                System.out.println(errorMessage);
            }
        }while (!conditionRacial);
        return bool;
    }

    public int readConditionIntField(String initMessage, String errorMessage, Predicate<Integer> predicate) {
        boolean condition = false;
        int intVar = 0;
        do {
            System.out.println(initMessage);
            try {
                intVar = readInt();
            }catch (InputMismatchException e) {
                System.out.println(errorMessage);
                continue;
            }
            if (predicate.test(intVar))
                condition = true;
            else
                System.out.println(errorMessage);
        }while (!condition);

        return intVar;
    }

    public Race readRace(String initMessage, String errorMessage) {
        Race race = null;
        do {
            System.out.println(initMessage);
            System.out.println("Możliwe rasy:" + Arrays.toString(Race.values()));
            try {
                race = Race.createOptionFromString(readString());
            } catch (NoSuchRaceException e) {
                System.out.println(errorMessage);
            }
        } while (race == null);
        return race;
    }

    public Skill readSkill(String initMessage, String errorMessage,SkillsRepository skillsRepository)
    {
        String skillName = "";
        Skill skill = null;
        do {
            System.out.println(initMessage);
            System.out.println("Możliwe zdolności:");
            for (Skill s : skillsRepository.getSkills())
            {
                System.out.print(s.getName() + ", ");
            }
            try {
                skillName = readString();
                Optional<Skill> optionalSkill = skillsRepository.findByName(skillName);
                if (optionalSkill.isPresent()) skill = optionalSkill.get();
                else throw new SkillNotFoundException("Nie znaleziono zdolności o nazwie " + skillName);
            } catch (InputMismatchException exception) {
                System.out.println(errorMessage);
            } catch (SkillNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } while (skill == null);
        return skill;
    }

    public HashSet<Race> readSetOfRaces(String initMessage) {
        HashSet<Race> races = new HashSet<>();

        boolean conditionRaces = false;
        do {
            System.out.println(initMessage);
            System.out.println("Możliwe rasy:" + Arrays.toString(Race.values()));
            try {
                String[] stringRaces = readString().replaceAll(" ","").split(",");
                for (String race : stringRaces) {
                    Race raceToAdd = Race.createOptionFromString(race);
                    races.add(raceToAdd);
                }
                conditionRaces = true;
            } catch (NoSuchRaceException e) {
                System.out.println(e.getMessage());
            }
        } while (!conditionRaces);
        return races;
    }

    public HashSet<Skill> readSetOfSkills(String initMessage, String errorMessage, SkillsRepository skillsRepository) {
        HashSet<Skill> skills = new HashSet<>();
        boolean conditionSkills = false;
        do {
            System.out.println(initMessage);
            System.out.println("Możliwe zdolności:");
            for(Skill skill : skillsRepository.getSkills()) {
                System.out.print(skill.getName() + ", ");
            }

            try {
                String[] stringSkills = readString().replaceAll(" ", "").split(",");
                for (String skill : stringSkills) {
                    Optional<Skill> opSkill = skillsRepository.findByName(skill);
                    if (opSkill.isPresent()) skills.add(opSkill.get());
                    else throw new SkillNotFoundException("Nie znaleziono zdolności o nazwie " + skill);
                }
                conditionSkills = true;
            } catch (InputMismatchException exception) {
                System.out.println(errorMessage);
            } catch (SkillNotFoundException exception) {
                System.out.println(exception.getMessage());
            }
        } while (!conditionSkills);
        return skills;
    }

    public HashSet<Ability> readSetOfAbilities(String initMessage, String errorMessage, AbilitiesRepository abilitiesRepository) {
        HashSet<Ability> abilities = new HashSet<>();
        boolean conditionAbilities = false;
        do {
            System.out.println(initMessage);
            System.out.println("Możliwe umiejętności:");
            for (Ability ability : abilitiesRepository.getAbilities()) {
                System.out.println(ability.getName() + ", ");
            }
            try {
                String[] stringAbilities = readString().replaceAll(" ","").split(",");
                for (String ability : stringAbilities) {
                    Optional<Ability> opAbility = abilitiesRepository.findByName(ability);
                    if(opAbility.isPresent()) abilities.add(opAbility.get());
                    else throw new SkillNotFoundException("Nie znaleziono umiejetności o nazwie " + ability);
                }
                conditionAbilities = true;
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
            } catch (SkillNotFoundException exception) {
                System.out.println(exception.getMessage());
            }
        } while (!conditionAbilities);
        return abilities;
    }
}

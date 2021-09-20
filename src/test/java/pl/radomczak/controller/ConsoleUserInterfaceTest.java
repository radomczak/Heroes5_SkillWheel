package pl.radomczak.controller;

import org.junit.*;
import pl.radomczak.controller.io.console.ConsoleUserInterface;
import pl.radomczak.model.*;


import java.util.Optional;

import static org.junit.Assert.*;

public class ConsoleUserInterfaceTest {

    private ConsoleUserInterface userInterface;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        userInterface = new ConsoleUserInterface();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void handleExitTest() {
        String consoleInput = "0" + (char)13;

        userInterface.changeReaderInputToString(consoleInput);
        userInterface.handle();
        assertTrue(true);
    }

    @Test
    public void handleAddAbilityTest() {
        WheelControl wheelControl = new WheelControl();
        userInterface.applyFor(wheelControl);

        StringBuilder sb = new StringBuilder("");
        sb.append(3);
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        sb.append("Description");
        sb.append("\r");
        sb.append("Image");
        sb.append("\r");
        sb.append(3);
        sb.append("\r");
        sb.append("RYCERZ,NEKROMANTA");
        sb.append("\r");
        sb.append("False");
        sb.append("\r");
        //Print
        sb.append(4);
        sb.append("\r");
        //Exit
        sb.append(0);
        sb.append("\r");

        String consoleInput = sb.toString();

        userInterface.changeReaderInputToString(consoleInput);
        userInterface.handle();

        Optional<Ability> ability = wheelControl.getAbilitiesRepository().findByName("NazwaA");

        if (ability.isPresent()) {
            Ability a = ability.get();
            assertEquals(a.getDescription(),"Description");
            assertEquals(a.getImage(),"Image");
            assertEquals(a.getProficiencyLevel(),3);
            assertTrue(a.getAllowedRaces().contains(Race.RYCERZ));
            assertTrue(a.getAllowedRaces().contains(Race.NEKROMANTA));
            assertFalse(a.isRacial());
            assertNull(a.getRequiredAbilities());
        } else {
            fail();
        }
    }

    @Test
    public void handleAddSkillTest() {
        WheelControl wheelControl = new WheelControl();
        userInterface.applyFor(wheelControl);

        StringBuilder sb = new StringBuilder("");
        //Ability
        sb.append(3);
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        sb.append("Description");
        sb.append("\r");
        sb.append("Image");
        sb.append("\r");
        sb.append(3);
        sb.append("\r");
        sb.append("RYCERZ,NEKROMANTA");
        sb.append("\r");
        sb.append("False");
        sb.append("\r");
        //Skill
        sb.append(1);
        sb.append("\r");
        sb.append("NazwaS");
        sb.append("\r");
        sb.append("Description");
        sb.append("\r");
        sb.append("Image");
        sb.append("\r");
        sb.append("RYCERZ");
        sb.append("\r");
        sb.append("False");
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        //Print
        sb.append(2);
        sb.append("\r");
        //Exit
        sb.append(0);
        sb.append("\r");

        String consoleInput = sb.toString();

        userInterface.changeReaderInputToString(consoleInput);
        userInterface.handle();

        Optional<Skill> skill = wheelControl.getSkillsRepository().findByName("NazwaS");
        Optional<Ability> ability = wheelControl.getAbilitiesRepository().findByName("NazwaA");

        if (skill.isPresent() && ability.isPresent()) {
            Skill s = skill.get();
            Ability a = ability.get();
            assertEquals(s.getDescription(),"Description");
            assertEquals(s.getImage(),"Image");
            assertEquals(s.getRace().toString(),"RYCERZ");
            assertNull(s.getRequiredSkills());
            assertTrue(s.getRequiredAbilities().contains(a));
        } else {
            fail();
        }
    }

    @Test
    public void handleAddHeroTest() {
        WheelControl wheelControl = new WheelControl();
        userInterface.applyFor(wheelControl);

        StringBuilder sb = new StringBuilder("");
        //Ability
        sb.append(3);
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        sb.append("Description");
        sb.append("\r");
        sb.append("Image");
        sb.append("\r");
        sb.append(3);
        sb.append("\r");
        sb.append("RYCERZ,NEKROMANTA");
        sb.append("\r");
        sb.append("False");
        sb.append("\r");
        //Skill
        sb.append(1);
        sb.append("\r");
        sb.append("NazwaS");
        sb.append("\r");
        sb.append("Description");
        sb.append("\r");
        sb.append("Image");
        sb.append("\r");
        sb.append("RYCERZ");
        sb.append("\r");
        sb.append("False");
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        //Hero
        sb.append(5);
        sb.append("\r");
        sb.append("ImieH");
        sb.append("\r");
        sb.append("NazwaS");
        sb.append("\r");
        sb.append("NazwaS");
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        sb.append("RYCERZ");
        sb.append("\r");
        //Exit
        sb.append(0);
        sb.append("\r");

        String consoleInput = sb.toString();

        userInterface.changeReaderInputToString(consoleInput);
        userInterface.handle();

        Optional<Skill> skill = wheelControl.getSkillsRepository().findByName("NazwaS");
        Optional<Ability> ability = wheelControl.getAbilitiesRepository().findByName("NazwaA");
        Optional<Hero> hero = wheelControl.getHeroesRepository().findByName("ImieH");

        if (skill.isPresent() && ability.isPresent() && hero.isPresent()) {
            Skill s = skill.get();
            Ability a = ability.get();
            Hero h = hero.get();
            assertEquals(h.getRace(),Race.RYCERZ);
            assertEquals(h.getUniqueSkill(), s);
            assertTrue(h.getStartingAbilities().contains(a));
            assertTrue(h.getStartingSkills().contains(s));
        } else {
            fail();
        }
    }

    @Test
    public void handleAddBuildTest() {
        WheelControl wheelControl = new WheelControl();
        userInterface.applyFor(wheelControl);

        StringBuilder sb = new StringBuilder("");
        //Ability
        sb.append(3);
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        sb.append("Description");
        sb.append("\r");
        sb.append("Image");
        sb.append("\r");
        sb.append(3);
        sb.append("\r");
        sb.append("RYCERZ,NEKROMANTA");
        sb.append("\r");
        sb.append("False");
        sb.append("\r");
        //Skill
        sb.append(1);
        sb.append("\r");
        sb.append("NazwaS");
        sb.append("\r");
        sb.append("Description");
        sb.append("\r");
        sb.append("Image");
        sb.append("\r");
        sb.append("RYCERZ");
        sb.append("\r");
        sb.append("False");
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        //Hero
        sb.append(5);
        sb.append("\r");
        sb.append("ImieH");
        sb.append("\r");
        sb.append("NazwaS");
        sb.append("\r");
        sb.append("NazwaS");
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        sb.append("RYCERZ");
        sb.append("\r");
        //Build
        sb.append(7);
        sb.append("\r");
        sb.append("NazwaB");
        sb.append("\r");
        sb.append("ImieH");
        sb.append("\r");
        sb.append("NazwaS");
        sb.append("\r");
        sb.append("NazwaA");
        sb.append("\r");
        //Exit
        sb.append(0);
        sb.append("\r");

        String consoleInput = sb.toString();

        userInterface.changeReaderInputToString(consoleInput);
        userInterface.handle();

        Optional<Skill> skill = wheelControl.getSkillsRepository().findByName("NazwaS");
        Optional<Ability> ability = wheelControl.getAbilitiesRepository().findByName("NazwaA");
        Optional<Hero> hero = wheelControl.getHeroesRepository().findByName("ImieH");
        Optional<Build> build = wheelControl.getBuildsRepository().findByName("NazwaB");

        if (skill.isPresent() && ability.isPresent() && hero.isPresent() && build.isPresent()) {
            Skill s = skill.get();
            Ability a = ability.get();
            Hero h = hero.get();
            Build b = build.get();

            assertEquals(b.getHero(),h);
            assertTrue(b.getAbilities().contains(a));
            assertTrue(b.getSkills().contains(s));
        } else {
            fail();
        }
    }
}

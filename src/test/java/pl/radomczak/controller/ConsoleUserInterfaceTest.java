package pl.radomczak.controller;

import org.junit.*;
import pl.radomczak.controller.io.ConsoleUserInterface;
import pl.radomczak.model.Ability;
import pl.radomczak.model.Race;


import java.util.Optional;

import static org.junit.Assert.*;

public class ConsoleUserInterfaceTest
{
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void handleExitTest() {
        ConsoleUserInterface userInterface = new ConsoleUserInterface();
        String consoleInput = "0" + (char)13;

        userInterface.changeReaderInputToString(consoleInput);
        userInterface.handle();
        assertTrue(true);
    }

    @Test
    public void handleAddAbilityTest() {
        WheelControl wheelControl = new WheelControl();
        ConsoleUserInterface userInterface = new ConsoleUserInterface();
        userInterface.applyFor(wheelControl);

        StringBuilder sb = new StringBuilder("");
        sb.append(3);
        sb.append((char)13);
        sb.append("Nazwa");
        sb.append((char)13);
        sb.append("Description");
        sb.append((char)13);
        sb.append("Image");
        sb.append((char)13);
        sb.append(3);
        sb.append((char)13);
        sb.append("RYCERZ,NEKROMANTA");
        sb.append((char)13);
        sb.append("False");
        sb.append((char)13);
        sb.append(4);
        sb.append((char)13);
        sb.append(0);
        sb.append((char)13);

        String consoleInput = sb.toString();

        userInterface.changeReaderInputToString(consoleInput);
        userInterface.handle();

        Optional<Ability> ability = wheelControl.getAbilitiesRepository().findByName("Nazwa");

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
        assertTrue(true);
    }
}

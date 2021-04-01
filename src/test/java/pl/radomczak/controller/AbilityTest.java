package pl.radomczak.controller;

import org.junit.*;
import pl.radomczak.model.Ability;
import pl.radomczak.model.Race;

import java.util.HashSet;

import static org.junit.Assert.*;

public class AbilityTest {

    Ability ability;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        HashSet<Race> races = new HashSet<>();
        races.add(Race.RYCERZ);
        races.add(Race.LORD_DEMONOW);

        ability = Ability.builder()
                .withName("Test name")
                .withDescription("description")
                .withImage("image")
                .withProficiencyLevel(2)
                .withAllowedRaces(races)
                .withRacial(false)
                .withRequiredAbilities(new HashSet<>())
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void toCSVTest() {
        System.out.println(ability.toCSV());
        assertEquals(ability.toCSV(),
                "Test name;description;image;2;false;RYCERZ,LORD_DEMONOW;;");
    }
}

package pl.radomczak.model;

import org.junit.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AbilityTest {

    Ability ability;
    Set<Race> races;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        races = new HashSet<>();
        races.add(Race.RYCERZ);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void builderTest() {
        ability = Ability.builder()
                .withName("Test name")
                .withDescription("description")
                .withImage("image")
                .withProficiencyLevel(2)
                .withAllowedRaces(races)
                .withRacial(false)
                .withRequiredAbilities(new HashSet<>())
                .build();

        assertEquals("Test name",ability.getName());
        assertEquals("description",ability.getDescription());
        assertEquals("image",ability.getImage());
        assertEquals(2,ability.getProficiencyLevel());
        assertEquals(races,ability.getAllowedRaces());
        assertFalse(ability.isRacial());
        assertNotNull(ability.getRequiredAbilities());
    }

    @Test
    public void toCSVTest() {
        ability = Ability.builder()
                .withName("Test name")
                .withDescription("description")
                .withImage("Test image")
                .withProficiencyLevel(1)
                .withAllowedRaces(races)
                .withRacial(false)
                .withRequiredAbilities(new HashSet<>())
                .build();

        assertEquals("Test name;description;Test image;1;false;RYCERZ;;",ability.toCSV());
    }
}

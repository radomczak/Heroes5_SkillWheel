package pl.radomczak.model;

import org.junit.*;

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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void builderTest() {
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
        HashSet<Race> races = new HashSet<>();
        races.add(Race.RYCERZ);
        races.add(Race.LORD_DEMONOW);

        ability = Ability.builder()
                .withName("Test name")
                .withDescription("description")
                .withImage("Test image")
                .withProficiencyLevel(1)
                .withAllowedRaces(races)
                .withRacial(false)
                .withRequiredAbilities(new HashSet<>())
                .build();

        assertEquals("Test name;description;Test image;1;false;RYCERZ,LORD_DEMONOW;;",ability.toCSV());
    }
}

package pl.radomczak.model;

import org.junit.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SkillTest {

    Skill skill;
    Set<Ability> abilities;
    Set<Race> races;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() {
        abilities = new HashSet<>();
        Ability ability;
        races = new HashSet<>();
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
        abilities.add(ability);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void builderTest() {
        skill = Skill.builder()
                .withName("Skill name")
                .withDescription("Skill desc")
                .withImage("skill.png")
                .withRequiredAbilities(abilities)
                .withAllowedRaces(races)
                .withRequiredSkills(new HashSet<>())
                .build();

        assertEquals("Skill name",skill.getName());
        assertEquals("Skill desc",skill.getDescription());
        assertEquals("skill.png",skill.getImage());
        assertNotNull(skill.getRequiredAbilities());
        assertEquals(abilities,skill.getRequiredAbilities());
        assertNotNull(skill.getAllowedRaces());
        assertNotNull(skill.getRequiredSkills());
    }

    @Test
    public void toCSVTest() {
        skill = Skill.builder()
                .withName("Skill name")
                .withDescription("Skill desc")
                .withImage("skill.png")
                .withRequiredAbilities(abilities)
                .withAllowedRaces(races)
                .withRequiredSkills(new HashSet<>())
                .build();

        assertEquals("Skill name",skill.getName());
        assertEquals("Skill desc",skill.getDescription());
        assertEquals("skill.png",skill.getImage());
        assertNotNull(skill.getRequiredAbilities());
        assertNotNull(skill.getAllowedRaces());
        assertNotNull(skill.getRequiredSkills());
    }
}

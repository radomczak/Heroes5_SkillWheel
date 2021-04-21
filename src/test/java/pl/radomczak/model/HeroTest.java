package pl.radomczak.model;

import org.junit.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class HeroTest {
    Hero hero;
    Skill skill;
    Set<Ability> abilities;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        abilities = new HashSet<>();
        Ability ability;
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

        abilities.add(ability);

        skill = Skill.builder()
                .withName("Skill name")
                .withDescription("Skill desc")
                .withImage("skill.png")
                .withRequiredAbilities(abilities)
                .withRace(Race.BARBARZYNCA)
                .withRequiredSkills(new HashSet<>())
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void builderTest() {
        hero = Hero.builder()
                .withName("Clavius Testus Maximus")
                .withUniqueSkill(skill)
                .withStartingSkills(new HashSet<>())
                .withStartingAbilities(abilities)
                .withRace(Race.RYCERZ)
                .build();

        assertEquals("Clavius Testus Maximus",hero.getName());
        assertNotNull(hero.getUniqueSkill());
        assertNotNull(hero.getStartingSkills());
        assertNotNull(hero.getStartingAbilities());
        assertEquals(skill,hero.getUniqueSkill());
        assertEquals(abilities,hero.getStartingAbilities());
        assertEquals(Race.RYCERZ,hero.getRace());
    }

    @Test
    public void toCSVTest() {
        hero = Hero.builder()
                .withName("Clavius Testus Maximus")
                .withUniqueSkill(skill)
                .withStartingSkills(new HashSet<>())
                .withStartingAbilities(abilities)
                .withRace(Race.RYCERZ)
                .build();
        assertEquals("Clavius Testus Maximus;Skill name;;Test name;RYCERZ;",hero.toCSV());
    }
}

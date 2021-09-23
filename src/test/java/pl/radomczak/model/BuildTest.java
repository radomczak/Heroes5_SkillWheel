package pl.radomczak.model;

import org.junit.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BuildTest {

    Build build;
    Hero hero;
    Set<Skill> skills;
    Set<Ability> abilities;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        skills = new HashSet<>();
        abilities = new HashSet<>();
        Skill skill;
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
                .withAllowedRaces(races)
                .withRequiredSkills(new HashSet<>())
                .build();
        skills.add(skill);

        hero = Hero.builder()
                .withName("Clavius Testus Maximus")
                .withUniqueSkill(skill)
                .withStartingSkills(new HashSet<>())
                .withStartingAbilities(abilities)
                .withRace(Race.RYCERZ)
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void builderTest() {
        build = Build.builder()
                .withName("Test build")
                .withHero(hero)
                .withSkills(skills)
                .withAbilities(abilities)
                .build();

        assertEquals("Test build",build.getName());
        assertNotNull(build.getSkills());
        assertNotNull(build.getAbilities());
        assertNotNull(build.getHero());
        assertEquals(skills,build.getSkills());
        assertEquals(abilities,build.getAbilities());
        assertEquals(hero,build.getHero());
    }

    @Test
    public void toCSVTest() {
        build = Build.builder()
                .withName("Test build")
                .withHero(hero)
                .withSkills(skills)
                .withAbilities(abilities)
                .build();
        assertEquals("Test build;Clavius Testus Maximus;Skill name;Test name;",build.toCSV());
    }
}

package pl.radomczak.controller;

import org.junit.*;
import static org.junit.Assert.*;

public class WheelControlTest
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
    public void testConstructor()
    {
        WheelControl w = new WheelControl();
        assertNotNull(w.getAbilitiesRepository());
        assertNotNull(w.getBuildsRepository());
        assertNotNull(w.getHeroesRepository());
        assertNotNull(w.getSkillsRepository());
        assertNotNull(w.getPositionRepository());
        assertNotNull(w.getWheel());
    }

}

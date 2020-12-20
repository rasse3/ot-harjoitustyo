package fi.rasmus.logic;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Rasmus
 */
public class DayTest {
    
    
    double seaPercentage = 0.25;
    StarControl SC = new StarControl();
    PlanetControl PC = new PlanetControl(seaPercentage);
    Logger Log = new Logger(PC.getPlantList());
    
    Day day;
    double sea_percentage;
    
    
    public DayTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        sea_percentage = 0.25;
        SC = new StarControl();
        PC = new PlanetControl(sea_percentage);
        Log = new Logger(PC.getPlantList());
        day = new Day(SC,PC,Log);
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testExecute(){
        sea_percentage = 0.25;
        SC = new StarControl();
        PC = new PlanetControl(sea_percentage);
        Log = new Logger(PC.getPlantList());
        day = new Day(SC,PC,Log);
        day.executeADay();
        assertEquals(1, SC.returnPhase());
    }
    
    
    
    
}

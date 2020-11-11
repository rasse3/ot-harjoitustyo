package fi.rasmus.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fi.rasmus.logic.StarControl;
import fi.rasmus.logic.FunctionHandler;
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
public class StarControlTest{
    
 StarControl SC;
 FunctionHandler FH;
    
    @Test
    public void hello() {
        
    }
    
  
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        SC = new StarControl(1,1.01,0.25);
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
    public void testStarPhaseIncrements() {
        SC = new StarControl(1,1.01,0.25);
        SC.incrementPhase();
        SC.incrementPhase();
        SC.incrementPhase();
        SC.incrementPhase();
        SC.incrementPhase();
        assertEquals(5.0, SC.returnPhase());
        
       
        
        
    }
    
    @Test
    public void testSameStatsAtStart(){
        SC = new StarControl(1,1.01,0.25);
        StarControl SC2 = new StarControl(1,1.01,0.25);
        
        SC.incrementPhase();
        SC.incrementPhase();
        SC.incrementPhase();
        SC.incrementPhase();
        SC.incrementPhase();
        
        SC2.incrementPhase();
        SC2.incrementPhase();
        SC2.incrementPhase();
        SC2.incrementPhase();
        SC2.incrementPhase();
        assertEquals(SC.radiationPowerThisDay(), SC2.radiationPowerThisDay());
        
    }
    
    
    @Test
    public void testStartingStats(){
        SC = new StarControl(2,2,0.5);
        assertEquals(2, SC.getBaseluminosity());
        assertEquals(2, SC.getFlareEfficient());
        assertEquals(0.5, SC.getFlarePropability());
    }
    
    
    @Test
    public void testSettingStats(){
        SC = new StarControl(1,1,0.55);
        SC.setStats(2,2,0.5);
        assertEquals(2, SC.getBaseluminosity());
        assertEquals(2, SC.getFlareEfficient());
        assertEquals(0.5, SC.getFlarePropability());
    }
   
    @Test
     public void testDefaultStartingStats(){
        SC = new StarControl();
        assertEquals(1, SC.getBaseluminosity());
        assertEquals(1.01, SC.getFlareEfficient());
        assertEquals(0.25, SC.getFlarePropability());
    }
    
    
    @Test
    public void testRandomSeedWorking(){
        SC = new StarControl();
        int apu = SC.getRandomSeed();
        SC.setRandomSeed(150);
        assertEquals(1000, apu);
        assertEquals(150, SC.getRandomSeed());
    }
    
    @Test 
    public void testChangedRandomSeed(){
        SC = new StarControl();
        StarControl SC2 = new StarControl();
        
        SC.incrementPhase();
        SC.incrementPhase();
        SC2.setRandomSeed(1200);
        SC2.resetRandom();
        SC.setRandomSeed(1200);
        SC.resetRandom();
        SC2.incrementPhase();
        SC2.incrementPhase();
       // The purpose of this function is to ensure that stars running in the same phase with the same randomseed 
       // lead to same result.
        
        assertEquals(SC.radiationPowerThisDay(), SC2.radiationPowerThisDay());
                
        
        
        
    }
    
    
    
    @Test 
    public void testChangedRandomSeedWithPhase(){
        
        SC = new StarControl();
        StarControl SC2 = new StarControl();
        
        SC.incrementPhase();
        SC.incrementPhase();
        SC2.setRandomSeed(1200);
        SC2.resetRandom();
        SC.setRandomSeed(1200);
        SC.resetRandom();
        SC2.setPhase(2.0);
       // The function tests that the same phases bring same values even when other one is set manually.
        
        assertEquals(SC.radiationPowerThisDay(), SC2.radiationPowerThisDay());
             
    }
    
    
    @Test
    public void testSynchronizingStarRandoms(){
            
        SC = new StarControl();
        StarControl SC2 = new StarControl();
        
        SC.incrementPhase();
        SC.incrementPhase();
        SC2.setRandomSeed(10);
        SC2.resetRandom();
        SC.setRandomSeed(100);
        SC.resetRandom();
        SC2.setPhase(2.0);
        SC2.incrementPhase();
        SC.setRandomSeed(111);
        SC2.setRandomSeed(111);
        SC.resetRandom();
        SC2.resetRandom();
        SC.setPhase(11.1);
        SC2.setPhase(11.1);
        
       // The function runs star evolution for some time and then resets randoms and sets stars to the same phase
       // in order to get the same results.
        
        assertEquals(SC.radiationPowerThisDay(), SC2.radiationPowerThisDay());
             
    }
    
    
    @Test
    public void testFunctionHandler(){
        
        SC = new StarControl();
        SC.setupFunctionHandler("sine", 1, 1);
        FH = new FunctionHandler();
        FH.setParameters("sine", 0.01, 0.03);
        
        assertEquals(SC.returnFunctionHandlerStats(), FH.toString());
        
    }
    
    
    @Test
    public void testFlawedInputsOnConstructor(){
        SC = new StarControl(-1, 0, 1000);
        assertEquals(1, SC.getBaseluminosity());
        assertEquals(1, SC.getFlareEfficient());
        assertEquals(0.25, SC.getFlarePropability());
        SC = new StarControl(-1, 0, -1000);
        assertEquals(0.25, SC.getFlarePropability());
    }
    
    @Test
      public void testFlawedInputsOnSettingStats(){
        SC = new StarControl();
        SC.setStats(0, 0.9, -0.1);
        assertEquals(1, SC.getBaseluminosity());
        assertEquals(1, SC.getFlareEfficient());
        assertEquals(0.25, SC.getFlarePropability());
        SC.setStats(0,0.9,100);
        assertEquals(0.25, SC.getFlarePropability());

        
    }
      
      
      
      @Test
      public void testFunctionHandlerSetUp(){
          SC = new StarControl();
          SC.setupFunctionHandler("sine", 0, 0);
          assertEquals(SC.returnFunctionHandlerStats(), "sine: amplitude: 0.01, frequency: 0.03");
          
          
      }
      
      @Test 
      public void testSetupFunctionHandler(){
          StarControl SC1 = new StarControl();
          StarControl SC2 = new StarControl();
          SC1.addAllowedFunctionToHandler("sine");
          SC1.setupFunctionHandler("sine", 1, 0.25);
          SC2.setupFunctionHandler("cosine", 1,1);
          
          assertEquals("sine: amplitude: 1.0, frequency: 0.25", SC1.returnFunctionHandlerStats());
          assertEquals("sine: amplitude: 0.01, frequency: 0.03", SC2.returnFunctionHandlerStats());
          SC1.setupFunctionHandler("sine", 1,-1);
          assertEquals("sine: amplitude: 1.0, frequency: 0.25", SC1.returnFunctionHandlerStats());
          SC1.setupFunctionHandler("sine", -1,-1);
          assertEquals("sine: amplitude: 1.0, frequency: 0.25", SC1.returnFunctionHandlerStats());
         }
    
      
      @Test
      public void testSetPhase(){
          SC = new StarControl();
          SC.setPhase(-500);
          assertEquals(0, SC.returnPhase());
      }
      

}
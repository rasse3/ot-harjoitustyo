/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.rasmus.logic;

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
public class FunctionHandlerTest {
    
    FunctionHandler handler = new FunctionHandler("sine", 12,2);
    
    public FunctionHandlerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
       handler  = new FunctionHandler("sine", 12,2);
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
    public void testConstructor(){
        handler  = new FunctionHandler("sine", 12,2);
        assertEquals(10.91156912190818 ,handler.getValue(1));
    }
    
    
    @Test
    public void testGetValues(){
        handler.setParameters("Cosine", 0, 0);
        assertEquals(0, handler.getValue(1) );
    }
    
    
    @Test
    public void testAddToAllowedFunctions(){
        handler.addToAllowedFunctions("sine");
        handler.addToAllowedFunctions("cosine");
        handler.addToAllowedFunctions("tan");
        handler.addToAllowedFunctions("hsine");
        handler.addToAllowedFunctions("hcosine");
        
        assertEquals(5, handler.getAllowedFunctions().size());
        assert(handler.getAllowedFunctions().contains("sine"));
        assert(!handler.getAllowedFunctions().contains("htan"));
        
    }
      
    
    
   
    
}

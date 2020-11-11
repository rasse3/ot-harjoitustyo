/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.rasmus.logic;

/**
 *
 * @author Rasmus
 */
public class Main {
    
    
    
    
    
    public static void main (String [] args){
        
        
        
        
        
        //These will be moved to a config file;
        Boolean logging = true;
        int loggingEveryTimeUnit = 1;   // 1 Day set as frequency of logging
        
        //Game world controller creations;
        StarControl SC = new StarControl();
        PlanetControl PC = new PlanetControl(0.75);
        Logger logger = new Logger();
        
        
        logger.setLogging(logging);
        logger.setTimeUnit(loggingEveryTimeUnit);
        Day day = new Day(SC, PC, logger);
        
        
        
           System.out.println(SC.returnFunctionHandlerStats());
        
        
        
        
        
        
        //Test copmutation of 100 days;
        for (int j = 0 ; j < 100 ; j++){
            day.executeADay();
            
            
        }
        
        
       //LOgger output
        for (Double dbl : logger.getPlans()){
            System.out.println(dbl);
            
        
        
    }
    
    
    
}
}

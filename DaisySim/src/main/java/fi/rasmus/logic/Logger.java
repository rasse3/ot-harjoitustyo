/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.rasmus.logic;
import java.util.ArrayList;
/**
 *
 * @author Rasmus
 */
public class Logger {
    
    
    boolean logging = true;
    int timeUnit = 1; // 1 Day
    int timeRan = 0;  // Helper for the computation of time-step
    ArrayList<Double> solar_luminosities;
    ArrayList<Double> planetary_temperatures;
    
    
    
    public Logger(){
        solar_luminosities = new ArrayList<>();
        planetary_temperatures = new ArrayList<>();
        
    }
    
    
    public void log(double solLum, double planTem){
        
       
        
        if(logging){
            
            timeRan++;
        
               
        
        if (timeRan%timeUnit == 0){
        
        solar_luminosities.add(solLum);
        planetary_temperatures.add(planTem);
        
        
        } 
        } 
    }
    
    
    
    public ArrayList<Double> getSols(){
        return solar_luminosities;
    }
    
    public ArrayList<Double> getPlans(){
        return planetary_temperatures;
    }
    
    public void setLogging(boolean boo){
        logging = boo;
    }
    
    public void setTimeUnit(int unit){
        this.timeUnit = unit;
    }
    
}

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
public class FunctionHandler {
    
    
    String function;
    ArrayList<String> allowedFunctions = new ArrayList<>();
    
    double amplitude;
    double frequency_radPerDay;
 
    public FunctionHandler(){
        this.function = "sine";
        this.amplitude = 0.01;
        this.frequency_radPerDay = 0.03;
            
            
        
    }
    
    public FunctionHandler(String type, double amplitude, double frequency){
     this.function = type;
     
     this.amplitude = amplitude;
     this.frequency_radPerDay = frequency;
    }
    
    
    
    public double getValue(double day){
        
        if(function.equals("sine")){
            return (amplitude* Math.sin(frequency_radPerDay* day));
        }
        
        
        return 0.0;
             
        
        
    }
    
    public ArrayList<String> getAllowedFunctions(){
        return allowedFunctions;
    }
    
    
    public void addToAllowedFunctions(String entry){
        allowedFunctions.add(entry);
    }
        
    
    public ArrayList<String> returnAllowedFunctions(){
        return allowedFunctions;
    }

    
    public void setParameters(String type, double amplitude, double frequency){
     this.function = type;
     
     this.amplitude = amplitude;
     this.frequency_radPerDay = frequency;
    }
    
    public String toString(){
        return this.function + ": amplitude: " + amplitude + ", frequency: " + frequency_radPerDay ;
    }
    
    
    
    
    
}


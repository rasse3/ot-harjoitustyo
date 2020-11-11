/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.rasmus.logic;
import java.util.Random;
/**
 *
 * @author Rasmus
 */
public class PlantSpecies {
    
    String name;
    double temperaturePreference;
    double albedo;
    double efficiencyCO2;
    double thresholdCO2;
    double productionO2;
    double spreading_rate;
    
    String[] nimet;
    
    
    
    
    
    
    public PlantSpecies(String name){
        
        
        
        
    }
    
    
    public String randomizeName(int seed) {
        Random random = new Random(seed);
        double helpdbl = random.nextDouble();
        int helpint = (int)( Math.floor(helpdbl*this.nimet.length) );
        return this.nimet[helpint];
    }
    
    public void setNameTable(String[] nimet){
        this.nimet = nimet;
    }
    
    
    
}

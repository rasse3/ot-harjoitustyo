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
public class PlanetControl {
    
    
    double albedo;
    double sea;
    double sea_albedo = 0.06;
    double carbon_dioxide;
    double methane;
    double oxygen;
    double watervapour;
    double cloudiness;
    Flora florae;
    
    
    public PlanetControl(double sea_percentage){
        
        this.sea = sea_percentage;
        this.florae = new Flora();
    }
    
    public double radiationAbsorption(double starIrradiance, double albedo)
    {
        return starIrradiance * albedo;
    }
    
    public double temperatureThisDay(double irrad){
        calculateAlbedo();
        return radiationAbsorption(irrad, albedo);
    }
    
    public void calculateAlbedo(){
        albedo = sea*sea_albedo;
        albedo = albedo + plantAlbedo();
        albedo = albedo + cloudiness;    
           
        }
    
    public double plantAlbedo(){
      
        
        
        return 0;
    }
   
    public double calculateTemperature(){
        
        double temperature = 0;
        
        
        
        
        return temperature;
    }
    
    
    
    
    
    
}
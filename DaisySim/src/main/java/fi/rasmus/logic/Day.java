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
public class Day {
    
    
    StarControl SC;
    PlanetControl PC;
    Logger log; 
    
    public Day(StarControl SC, PlanetControl PC, Logger logger){
        this.SC = SC;
        this.PC = PC;
        this.log = logger;
    }
    
    
    public void executeADay()
    {
        SC.incrementPhase();
        double radiance = SC.radiationPowerThisDay();
        double temperature = PC.temperatureThisDay(radiance);
        log.log(radiance, temperature);
    }
    
    
    
    
}

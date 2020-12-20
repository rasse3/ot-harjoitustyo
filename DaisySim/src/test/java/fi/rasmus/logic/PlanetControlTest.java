/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.rasmus.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Rasmus
 */



public class PlanetControlTest {
    
    
    PlanetControl pc = new PlanetControl(0.5);
    StarControl sc = new StarControl();
    Logger log = new Logger(pc.getPlantList());
    
    Day day = new Day(sc,pc,log);
    
    
        SpeciesP p1 = new SpeciesP("Plant1", 0.3 , 293, 1, 1 );
        SpeciesP p2 = new SpeciesP("Plant2", 0.6 , 293, 1, 1 );
        
        Flora flora = new Flora(0.25);
        
        
        public void addSpecies(){
        
        flora.addSpecies(p1);
        flora.addSpecies(p2);
        
        p1.setCoverage(0.5);
        p2.setCoverage(0.5);
        
        assertEquals(1, flora.getTotalCoverage());
        assertEquals(0.5, flora.countAlbedo());
       
        assertEquals(0.045, pc.getAlbedo());
       
        
        
    
    
        }
    
}

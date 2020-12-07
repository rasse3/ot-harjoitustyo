package fi.rasmus.logic;
import java.util.ArrayList;
/**
 * Day is the class that controls other classes of the simulation.
 *
 * @author Rasmus
 */
public class Day {

    StarControl sc;
    PlanetControl pc;
    Logger log;

    /**
     * This is a constructor for a day-object. It sets the first values of planetary temperature and solar luminosity.
     *
     * @param sc A StarControl-object that controls the star
     * @param pc A PlanetControl-object that controls the planet and life on it
     * @param logger A logger-object that logs events on planet
     */
    public Day(StarControl sc, PlanetControl pc, Logger logger) {
        this.sc = sc;
        this.pc = pc;
        this.log = logger;
        log.log(sc.setInitial(), pc.setInitial(sc.setInitial()));
       
    }

    /**
     * This function completes 1 day in the simulation.
     */
    public void executeADay() {
        sc.incrementPhase();
        double previousTemp = log.getNewestPlanetTemperature();
        
        double radiance = sc.radiationPowerThisDay();
        double emission = pc.radiationEmission(previousTemp);
        double temperature = pc.temperatureThisDay(radiance, emission);
        
        
        log.log(radiance, temperature);
        
        ArrayList<SpeciesP> plants = pc.getPlantList();
        
        
        for (SpeciesP plant  : plants){
            double coverage = plant.getCoverage();
            log.logPlants(plant, coverage);
        }
        
        
    }

}

package fi.rasmus.logic;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Logger keeps track of events on the planet and the star. It contains info
 * about species of plants and animals, temperature of planet, albedo of planet
 * and irradiance of the star.
 *
 * @author Rasmus
 */
public class Logger {

    boolean logging = true;
    int timeUnit = 1; // 1 Day
    int timeRan = 0;  // Helper for the computation of time-step
    ArrayList<Double> solarLuminosities;
    ArrayList<Double> planetaryTemperatures;
    HashMap<SpeciesP, Double> coverages;
    public ArrayList<SpeciesP> plantsAsList = new ArrayList<>();

    /**
     * Constructor for the logger-object.
     * @param list the list of planetary values
     */
    public Logger(ArrayList<SpeciesP> list) {
        solarLuminosities = new ArrayList<>();
        planetaryTemperatures = new ArrayList<>();
        coverages = new HashMap<>();
        plantsAsList = list;
        System.out.println(list);

    }

    /**
     * Logging function for solar irradiance and planetary temperature.
     *
     * @param solLum Solar luminosity to be tracked
     * @param planTem Planetary temperature to be tracked
     */
    public void log(double solLum, double planTem) {

        if (logging) {

            timeRan++;

            if (timeRan % timeUnit == 0) {

                solarLuminosities.add(solLum);
                planetaryTemperatures.add(planTem);

            }
        }
    }

   
    
    /**
     * Logger of plant coverages.
     *
     * @param plant Plant-object
     * @param coverage Plant coverage of planetary surface
     */
    public void logPlants(SpeciesP plant, double coverage) {

        if (coverages.containsKey(plant)) {

            this.coverages.replace(plant, coverage);
        } else {
            this.coverages.put(plant, coverage);
        }
    }

    public ArrayList<Double> getSols() {
        return solarLuminosities;
    }

    public ArrayList<Double> getPlans() {
        return planetaryTemperatures;
    }

    public void setLogging(boolean boo) {
        logging = boo;
    }

    public void setTimeUnit(int unit) {
        this.timeUnit = unit;
    }

    public double getNewestPlanetTemperature() {
        return this.planetaryTemperatures.get(this.planetaryTemperatures.size() - 1);
    }

    
    
    public double getNewestSolarLuminosity() {
        return this.solarLuminosities.get(this.solarLuminosities.size() - 1);

    }
    
    /**
     * Returns current coverage of a plant-object.
     * @param p Plant-object to use as key
     * @return Coverage of planet by that plant 
     */

    public double getNewestCoverage(SpeciesP p) {

        return p.getCoverage();

    }

}

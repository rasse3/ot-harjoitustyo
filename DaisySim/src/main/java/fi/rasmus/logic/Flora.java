package fi.rasmus.logic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the properties of plants on the planet.
 *
 * @author Rasmus
 */
public class Flora {

    HashMap<SpeciesP, Double> growthFactors;
    ArrayList<SpeciesP> plantSpecies;
    double seaPercentage;
    double planetaryTemperature;
    double planetaryAlbedo;
    double freeArea;

    // Constants
    double deathRate = 0.2;

    /**
     * This method creates a flora-object that will control the plantlife on the
     * planet.
     *
     * @param seaPercentage The amount of surface area that will not have plant
     * life
     */
    public Flora(double seaPercentage) {

        plantSpecies = new ArrayList<>();
        growthFactors = new HashMap<>();
        this.seaPercentage = seaPercentage;
        this.freeArea = 1 - seaPercentage;

    }

    /**
     * Sets planetary albedo value.
     *
     * @param albedo Planetary albedo
     */
    public void setAlbedo(double albedo) {
        planetaryAlbedo = albedo;
    }

   /**
    * Returns total albedo of planet.
    * @return albedo the albedo of the planet
    */
    
    public double getTotalAlbedo() {
        double albedo = 0;
        double freeGround = 1;
        for (SpeciesP plant : plantSpecies) {
            albedo = albedo + plant.getCoverage() * plant.getAlbedo();
            freeGround = freeGround - plant.getCoverage();
        }
        albedo = (albedo + freeGround * 0.5);
        if (albedo > 1) {
            albedo = 1;

        }
        if (albedo < 0) {
            albedo = 0;
        }
        return albedo;

    }

    /**
     * Sets planetary temperature.
     *
     * @param temperature Planetary temperature
     */
    public void setTemperature(double temperature) {
        planetaryTemperature = temperature;
    }

    /**
     * Adds species to the list of plant species.
     *
     * @param plant The object to be added to the list of plant specise
     */
    public void addSpecies(SpeciesP plant) {
        this.plantSpecies.add(plant);
        this.growthFactors.put(plant, 0.0);
    }

    /**
     * Returns the total coverage of (non-sea) surface of the planet by plants.
     *
     * @return The total coverage value
     */
    public double getTotalCoverage() {

        double totalCoverage = 0;
        for (SpeciesP species : plantSpecies) {
            totalCoverage = totalCoverage + species.getCoverage();
        }
        if (totalCoverage > 1) {
            return 1;
        } else if (totalCoverage < 0) {
            return 0;
        }
        return totalCoverage;
    }

    /**
     * Counts the albedo of the planet depending on the plant-life.
     *
     * @return The value of planetary albedo
     */
    public double countAlbedo() {

        double plantAlbedo = 0;
        for (SpeciesP species : plantSpecies) {
            plantAlbedo = plantAlbedo + species.albedo * species.getCoverage();
        }

        double total = getTotalCoverage();

        return plantAlbedo;
    }

    /**
     * Counts how much the plants use carbon dioxide per day.
     *
     * @return The amount of carbon dioxide used by plants per day
     */
    public double countCo2Usage() {

        return 0;
    }

    /**
     * Counts how much the plants release oxygen per day.
     *
     * @return The amount of oxygen freed by plants per day
     */
    public double countO2Production() {

        return 0;
    }

    /**
     * Calculates growth factors to a hashmap to be used by the method that
     * calculates new plant coverage values.
     */
    public void calculateGrowthFactors() {

        for (SpeciesP plant : plantSpecies) {
            plant.setPlanetaryAlbedo(planetaryAlbedo);
            plant.setPlanetaryTemperature(planetaryTemperature);
            double factor = plant.getGrowthFactor();
            growthFactors.replace(plant, factor);

        }
    }

    /**
     * Calculates new coverages.
     */
    public void changeCoverages() {

        double totalChange = 0;

        for (SpeciesP plant : plantSpecies) {
            totalChange = totalChange + changeInCoverage(plant);
        }

        double changeFactor = freeArea;
        if (totalChange > freeArea) {
            changeFactor = freeArea - totalChange;
        }

        for (SpeciesP plant : plantSpecies) {
            plant.setCoverage(plant.getCoverage() + changeFactor * changeInCoverage(plant));
        }

        freeArea = freeArea - totalChange;

    }

    /**
     * Calculates changes in the coverage of all plants.
     *
     * @param p Plant to manipulate
     * @return Area change
     */
    public double changeInCoverage(SpeciesP p) {

        double coverage = p.getCoverage();

        double growthFactor = growthFactors.get(p);

        double change = coverage * (freeArea * growthFactor - deathRate) + 0.01;   // The constant 0.01 in the end sees to it that the plants don't die out

        return change;
    }

    /**
     * Returns list of existing plant species.
     *
     * @return List of plant-species
     */
    public ArrayList<SpeciesP> getPlantList() {
        return this.plantSpecies;
    }

    /**
     * Get coverage of planet with the species as key.
     *
     * @param plant Key plant-object
     * @return Coverage of the plant
     */
    public double getCoverage(SpeciesP plant) {
        return plant.getCoverage();
    }

}

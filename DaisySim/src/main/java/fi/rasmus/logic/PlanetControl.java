package fi.rasmus.logic;

import java.util.ArrayList;

/**
 * This class handles data about the planetary life, atmospheric gases, albedo
 * and temperature.
 *
 * @author Rasmus
 */
public class PlanetControl {

    double albedo;
    double sea;
    double carbonDioxide;
    double methane;
    double oxygen;
    double watervapour;
    double cloudiness;
    double previousTemperature;
    double landArea;
    double emissivityFactored;   // This is the emissivity with contribution of greenhouse effect
    // of atmospheric gases.
    Flora florae;

    // CONSTANTS:
    double basicEmissivity = 0.793;
    double ksb = 5.67 * (Math.pow(10, -8)); // Stefan-Boltzmann constant
    double area = 510100 * (10 ^ 6); // Surface area of the earth in square meters
    double wattsPerLuminosityUnit = 1360; // The absolute power per luminosity unit
    double cloudAlbedo = 0.5;
    double seaAlbedo = 0.06;
    double unCoveredAlbedo = 0.5;

    /**
     * Constructor that specifies how much of the planet's surface is available
     * for plants. The gases are set as relative fractions of earth values.
     *
     * @param seaPercentage The percentage of the surface not available for
     * plants
     */
    public PlanetControl(double seaPercentage) {

        this.sea = seaPercentage;
        this.landArea = (1 - sea) * area;
        this.florae = new Flora(this.sea);
        this.oxygen = 1;
        this.carbonDioxide = 1;
        this.methane = 1;
        this.cloudiness = 0;
    }

    /**
     * Returns the initial temperature of the planet to be used by the logger.
     * @param luminosity The luminosity of the sun 
     * @return The initial temperature of the planet.
     */
    public double setInitial(double luminosity) {
        calculateAlbedo();
        double absoluteLuminosity = 1360 * luminosity;
        double result = absoluteLuminosity * (1 - albedo);
        result = result / (4 * ksb);
        return Math.pow(result, 0.25);
    }

    /**
     * Calculates the absorbed radiation as function of irradiance and albedo.
     *
     * @param starIrradiance The current radiation amount coming from the star
     * @param albedo The current albedo of the planet
     * @return The amount of energy absorbed on the planet
     */
    public double radiationAbsorption(double starIrradiance, double albedo) {
        return starIrradiance * (1 - albedo);
    }

    /**
     * Calculates temperature from total absorbed energy.
     *
     * @param energyBalance Amount of energy incoming
     * @return Temperature from energy
     */
    public double radiationToTemperature(double energyBalance) {

        double energy = energyBalance / (ksb);

        return Math.pow(energy, 0.25);
    }

    /**
     * The temperature of the planet as function of absorbed radiation and
     * emitted radiation.
     *
     * @param radiationIncoming Incoming radiation
     * @param radiationToEmit Emitted radiation
     * @return Temperature of the planet
     */
    public double temperatureThisDay(double radiationIncoming, double radiationToEmit) {
        calculateAlbedo();
        calculateEmissivityFactor();
        double radiationAbsorbed = radiationAbsorption(radiationIncoming, albedo);
        double radiationEmitted = radiationEmission(previousTemperature);
        double energyBalance = radiationAbsorbed - radiationEmitted;
        double temperature = radiationToTemperature(energyBalance);

        return temperature;
    }

    /**
     * Updates the emissivityFactored-variable;
     */
    public void calculateEmissivityFactor() {
        emissivityFactored = basicEmissivity + carbonDioxide * 0.02 + methane * 0.004;
    }

    /**
     * Calculates emission of radiation increased by temperature and decreased
     * by greenhouse gases.
     *
     * @param oldTemperature Previous value of temperature
     * @return Amount of radiation emitted by the planet
     */
    public double radiationEmission(double oldTemperature) {

        double emission = ksb * emissivityFactored * landArea * (oldTemperature) * (oldTemperature) * (oldTemperature) * (oldTemperature);

        return emission;
    }

    /**
     * Calculates albedo of planet as function of plant-life.
     */
    public void calculateAlbedo() {
        albedo = sea * seaAlbedo;
        albedo = (1 - sea) * (florae.getTotalCoverage() * plantAlbedo() + (1 - florae.getTotalCoverage()) * unCoveredAlbedo);
        albedo = albedo - cloudiness * albedo + cloudiness * cloudAlbedo;

    }

    /**
     * Returns the value of plant-albedo from the Flora-object.
     *
     * @return The value of albedo of plants as average
     */
    public double plantAlbedo() {

        double plantAlbedo = florae.countAlbedo();

        return plantAlbedo;

    }

    /**
     * Returns the albedo of planet.
     *
     * @return The albedo value
     */
    public double getAlbedo() {
        return this.albedo;
    }

    /**
     * Add a plant species to the list of flora.
     *
     * @param plant The species to add to the list of plant species
     */
    public void addPlantSpecies(SpeciesP plant) {
        this.florae.addSpecies(plant);
    }

    /**
     * Return methane amount in the atmosphere as percentage compared to earth.
     *
     * @return Methane amount as related to values on earth
     */
    public double getMethane() {
        return this.methane;
    }

    /**
     * Return carbon dioxide amount in the atmosphere as percentage compared to
     * earth.
     *
     * @return Carbon dioxide amount as related to values on earth
     */
    public double getCarbonDioxide() {
        return this.carbonDioxide;
    }

    /**
     * Return oxygen amount in the atmosphere as percentage compared to earth.
     *
     * @return Oxygen amount as related to values on earth
     */
    public double getOxygen() {
        return this.oxygen;
    }

    /**
     * Sets gas amounts.
     *
     * @param cO2 Carbon dioxide amount
     * @param methnane Methane amount
     * @param oxygen Oxygen amount
     */
    public void setGases(double cO2, double methnane, double oxygen) {
        this.carbonDioxide = cO2;
        this.methane = methane;
        this.oxygen = oxygen;
    }

    public ArrayList<SpeciesP> getPlantList() {
        return florae.getPlantList();
    }

    
    /**
     * Returns plant coverage amounts as searched by a plant species-object.
     * @param plant Plant-object to use as key for search
     * @return The coverage of searched plant
     */
    
    public double plantCoverage(SpeciesP plant) {
        if (florae.getPlantList().contains(plant)) {
            return plant.getCoverage();
        } else {
            return 0.0;
        }
    }

}

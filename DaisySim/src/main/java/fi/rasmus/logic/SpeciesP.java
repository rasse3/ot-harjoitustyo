package fi.rasmus.logic;

/**
 * This class contains a single plant species and its related values.
 *
 * @author Rasmus
 */
public class SpeciesP {

    String name;
    double albedo;
    double co2Usage;
    double co2Threshold;
    double idealTemperature;
    double coverage;
    double growthFactor;
    double localTemperature;
    double planetaryAlbedo;
    double planetaryTemperature;

    // Constants
    double fha = 20;    // This is the factor which determines difference in local and planetary temperatures

    /**
     * Constuctor for a single plant species.
     *
     * @param name Name of the species
     * @param albedo Percentage of light reflected off the species
     * @param idealTemperature Temperature most suited for the species
     * @param co2Usage not used in this version
     * @param co2Threshold not used in this version
     */
    public SpeciesP(String name, double albedo, double idealTemperature, double co2Usage, double co2Threshold) {

        this.name = name;
        this.albedo = albedo;
        this.idealTemperature = 299.5;
        this.co2Usage = co2Usage;
        this.co2Threshold = co2Threshold;
        this.coverage = 0.05;
        this.growthFactor = 0;
        this.localTemperature = 0;
        this.planetaryAlbedo = 0;
        this.planetaryTemperature = 0;
    }

    /**
     * Calculates local temperature according to planetary values.
     */
    public void calculateLocalTemperature() {
        localTemperature = fha * (planetaryAlbedo - albedo) + planetaryTemperature;
    }

    /**
     * Sets planetary albedo.
     *
     * @param albedo The albedo of the whole planet
     */
    public void setPlanetaryAlbedo(double albedo) {
        this.planetaryAlbedo = albedo;
    }

    /**
     * Sets planetary temperature.
     *
     * @param temperature The temperature of the planet.
     */
    public void setPlanetaryTemperature(double temperature) {
        this.planetaryTemperature = temperature;
    }

    /**
     * Returns growth factor of plant for the planetcontrol-object.
     *
     * @return The growth factor of the plant species
     */
    public double getGrowthFactor() {
        return this.growthFactor;
    }

    /**
     * Calculates the rate of growth in given temperature.
     *
     *@return growthFactor the growth parameter
     */
    public double calculateGrowthFactor() {

        growthFactor = 1 - (0.003265) * Math.pow((idealTemperature - localTemperature), 2);
        return growthFactor;
    }

    /**
     * Set the name of the species.
     *
     * @param name Name to be given to the species
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the species.
     *
     * @return Name of the species
     */
    public String getname() {
        return this.name;
    }

    /**
     * Sets albedo of the plant species.
     *
     * @param albedo Albedo value to set for the species
     */
    public void setAlbedo(double albedo) {
        this.albedo = albedo;
    }

    /**
     * Returns the albedo-value of the species.
     *
     * @return The albedo of the species
     */
    public double getAlbedo() {
        return this.albedo;
    }

    /**
     * Sets the ideal temperature of the plant species.
     *
     * @param idealTemperature Temperature to set as ideal
     */
    public void setIdealTemperature(double idealTemperature) {
        this.idealTemperature = idealTemperature;
    }

    /**
     * Returns the ideal temperature value for the species.
     *
     * @return The species' ideal temperature
     */
    public double getIdealTemperature() {
        return this.idealTemperature;
    }

    /**
     * Set the coverage of the available surface of the planet occupied by this
     * species.
     *
     * @param coverage Coverage to set as the surface occupied by the species
     */
    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    /**
     * Returns the available (non-sea) area covered by the species.
     *
     * @return The land area covered by the species
     */
    public double getCoverage() {
        return this.coverage;
    }

}

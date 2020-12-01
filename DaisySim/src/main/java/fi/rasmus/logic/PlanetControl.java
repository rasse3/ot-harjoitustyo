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
    double seaAlbedo = 0.06;
    double carbonDioxide;
    double methane;
    double oxygen;
    double watervapour;
    double cloudiness;
    double cloudAlbedo;
    Flora florae;

    public PlanetControl(double seaPercentage) {

        this.sea = seaPercentage;
        this.florae = new Flora(this.sea);
        this.oxygen = 20;
        this.carbonDioxide = 0.04;
        this.methane = 0.0002;
    }

    public double radiationAbsorption(double starIrradiance, double albedo) {
        return starIrradiance * albedo;
    }

    public double temperatureThisDay(double irrad) {
        calculateAlbedo();
        return radiationAbsorption(irrad, albedo);
    }

    public void calculateAlbedo() {
        albedo = sea * seaAlbedo;
        albedo = albedo + plantAlbedo();
        albedo = albedo - cloudiness * albedo + cloudiness * cloudAlbedo;

    }

    public double plantAlbedo() {

        double plantAlbedo = florae.countAlbedo();

        return plantAlbedo;
    }

    public double calculateTemperature() {

        double temperature = 0;

        return temperature;
    }

    public double getAlbedo() {
        return this.albedo;
    }

    public void addPlantSpecies(SpeciesP plant) {
        this.florae.addSpecies(plant);
    }

    public double getMethane() {
        return this.methane;
    }

    public double getCarbonDioxide() {
        return this.carbonDioxide;
    }

    public double getOxygen() {
        return this.oxygen;
    }

    public void setGases(double cO2, double methnane, double oxygen) {
        this.carbonDioxide = cO2;
        this.methane = methane;
        this.oxygen = oxygen;
    }

}

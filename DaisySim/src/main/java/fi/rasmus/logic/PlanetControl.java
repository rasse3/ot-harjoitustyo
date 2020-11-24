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

}

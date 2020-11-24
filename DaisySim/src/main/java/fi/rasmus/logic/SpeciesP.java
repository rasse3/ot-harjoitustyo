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
public class SpeciesP {

    String name;
    double albedo;
    double co2Usage;
    double co2Threshold;
    double idealTemperature;
    double coverage;

    public SpeciesP(String name, double albedo, double idealTemperature, double co2Usage, double co2Threshold) {

        this.name = name;
        this.albedo = albedo;
        this.idealTemperature = idealTemperature;
        this.co2Usage = co2Usage;
        this.co2Threshold = co2Threshold;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getname() {
        return this.name;
    }

    public void setAlbedo(double albedo) {
        this.albedo = albedo;
    }

    public double getAlbedo() {
        return this.albedo;
    }

    public void setIdealTemperature(double idealTemperature) {
        this.idealTemperature = idealTemperature;
    }

    public double getIdealTemperature() {
        return this.idealTemperature;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public double getCoverage() {
        return this.coverage;
    }

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.rasmus.logic;
import java.util.ArrayList;
/**
 *
 * @author Rasmus
 */
public class Flora {

    ArrayList<SpeciesP> plantSpecies;
    double seaPercentage;

    public Flora(double seaPercentage) {

        plantSpecies = new ArrayList<>();

    }

    public void addSpecies(SpeciesP plant) {
        this.plantSpecies.add(plant);
    }

    public double getTotalCoverage() {

        double totalCoverage = 0;
        for (SpeciesP species : plantSpecies) {
            totalCoverage = totalCoverage + species.getCoverage();
        }
        return totalCoverage;
    }

    public double countAlbedo() {

        double plantAlbedo = 0;
        for (SpeciesP species : plantSpecies) {
            plantAlbedo = plantAlbedo + species.albedo * species.getCoverage();
        }
        double total = getTotalCoverage();

        return plantAlbedo * total;
    }

    public double countCo2Usage() {

        return 0;
    }

    public double countO2Production() {

        return 0;
    }

    public void calculateCoverages() {

    }

}

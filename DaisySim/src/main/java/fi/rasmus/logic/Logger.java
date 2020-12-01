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
public class Logger {

    boolean logging = true;
    int timeUnit = 1; // 1 Day
    int timeRan = 0;  // Helper for the computation of time-step
    ArrayList<Double> solarLuminosities;
    ArrayList<Double> planetaryTemperatures;

    public Logger() {
        solarLuminosities = new ArrayList<>();
        planetaryTemperatures = new ArrayList<>();

    }

    public void log(double solLum, double planTem) {

        if (logging) {

            timeRan++;

            if (timeRan % timeUnit == 0) {

                solarLuminosities.add(solLum);
                planetaryTemperatures.add(planTem);

            }
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

    public double getNewestPlanetTemperature(){
        return this.planetaryTemperatures.get(this.planetaryTemperatures.size()-1);
    }
    
    public double getNewestSolarLuminosity(){
        return this.solarLuminosities.get(this.solarLuminosities.size()-1);
        
    }
    
    
}

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
public class Day {

    StarControl sc;
    PlanetControl pc;
    Logger log;

    public Day(StarControl sc, PlanetControl pc, Logger logger) {
        this.sc = sc;
        this.pc = pc;
        this.log = logger;
    }

    public void executeADay() {
        sc.incrementPhase();
        double radiance = sc.radiationPowerThisDay();
        double temperature = pc.temperatureThisDay(radiance);
        log.log(radiance, temperature);
    }

}

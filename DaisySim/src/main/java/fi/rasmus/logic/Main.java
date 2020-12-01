/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.rasmus.logic;

import fi.rasmus.gui.Config;
import fi.rasmus.gui.SolarPowerChart;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

/**
 *
 * @author Rasmus
 */
public class Main {

    public static void main(String[] args) throws Exception {

        //These will be moved to a config file;
        Boolean logging = true;
        int loggingEveryTimeUnit = 1;   // 1 Day set as frequency of logging

        //Game world controller creations;'
        Config config = new Config();
        StarControl sc = new StarControl();
        PlanetControl pc = new PlanetControl(0.75);
        Logger logger = new Logger();
        Day day = new Day(sc, pc, logger);

        SolarPowerChart solPower = new SolarPowerChart(logger, pc, sc, day);

        logger.setLogging(logging);
        logger.setTimeUnit(loggingEveryTimeUnit);

        System.out.println(sc.returnFunctionHandlerStats());

        //Test copmutation of 100 days;
        for (int j = 0; j < 100; j++) {
            day.executeADay();

        }

        //LOgger output for solar irradiance
        for (Double dbl : logger.getSols()) {

            System.out.println(dbl);
        }
        //LOgger output for temperature
        for (Double dbl : logger.getPlans()) {

            System.out.println(dbl);
        }

        solPower.go();
        day.executeADay();

        double phase = 0;
        double[][] data = new double[1][1];

    }

}

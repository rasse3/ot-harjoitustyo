package fi.rasmus.logic;

import fi.rasmus.gui.Config;
import fi.rasmus.gui.SolarPowerChart;

/**
 * This is the main class of the program.
 *
 * @author Rasmus
 */
public class Main {

    /**
     * This is the main-method of the program.
     *
     * @param args Arguments to be passed at run-time
     * @throws Exception Thrown exception
     */
    public static void main(String[] args) throws Exception {

        //These will be moved to a config file;
        Boolean logging = true;
        int loggingEveryTimeUnit = 1;   // 1 Day set as frequency of logging

        //Game world controller creations;'
        Config config = new Config();
        StarControl sc = new StarControl();
        PlanetControl pc = new PlanetControl(0.5);
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

        SpeciesP p1 = new SpeciesP("Plant1", 0.3, 293, 1, 1);
        SpeciesP p2 = new SpeciesP("Plant2", 0.6, 293, 1, 1);

        Flora flora = new Flora(0);

        flora.addSpecies(p1);
        flora.addSpecies(p2);

        p1.setCoverage(0.5);
        p2.setCoverage(0.5);

        logger.logPlants(p1, 0.0);

        logger.logPlants(p2, 0.0);

        solPower.go();
        day.executeADay();

        double phase = 0;
        double[][] data = new double[1][1];

        System.out.println(flora.countAlbedo());
        System.out.println(flora.getTotalCoverage());

        pc.calculateAlbedo();
        System.out.println(pc.getAlbedo());

    }

}

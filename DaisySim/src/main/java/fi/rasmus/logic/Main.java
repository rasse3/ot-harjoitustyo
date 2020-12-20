package fi.rasmus.logic;

import fi.rasmus.gui.Config;
import fi.rasmus.gui.SolarPowerChart;
import java.util.Scanner;

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
        Logger logger = new Logger(pc.getPlantList());
        Day day = new Day(sc, pc, logger);
        Scanner scanner = new Scanner(System.in);
        
        SolarPowerChart solPower = new SolarPowerChart(logger, pc, sc, day);

        logger.setLogging(logging);
        logger.setTimeUnit(loggingEveryTimeUnit);

        System.out.println(sc.returnFunctionHandlerStats());

        
        
        boolean acceptableInput = false;
        System.out.println("Syötä tähden vaihtelun amplitudi");
        String amplitudeStr = scanner.nextLine();
        double amplitude = 0;
        while (!acceptableInput){
            try {
                amplitude = Double.parseDouble(amplitudeStr); 
            } catch (NumberFormatException e) {
                System.out.println("Syötä liukuluku väliltä 0-1");
                amplitudeStr = scanner.nextLine();
            } 
            if (amplitude >= 0 && amplitude <= 1){
                acceptableInput = true;
            } else {
                System.out.println("Syötä luku väliltä 0-1");
                amplitudeStr = scanner.nextLine();
            }
        }
        
        
        acceptableInput = false;
        System.out.println("Syötä tähden vaihtelun taajuus (radiaania päivässä");
        String frequencyStr = scanner.nextLine();
        double frequency = 0;
        while (!acceptableInput){
            try {
                frequency = Double.parseDouble(frequencyStr); 
            } catch (NumberFormatException e) {
                System.out.println("Syötä liukuluku väliltä 0-100");
                frequencyStr = scanner.nextLine();
            } 
            if (frequency >= 0 && frequency <= 100){
                acceptableInput = true;
            } else {
                System.out.println("Syötä luku väliltä 0-100");
                frequencyStr = scanner.nextLine();
            }
        }
        
        
        acceptableInput = false;
        System.out.println("Syötä tähden purkauksen kuumuus suhteessa perustasoon");
        String flareEffStr = scanner.nextLine();
        double flareEff = 0;
        while (!acceptableInput){
            try {
                flareEff = Double.parseDouble(flareEffStr); 
            } catch (NumberFormatException e) {
                System.out.println("Syötä liukuluku väliltä 0.5 - 1.5");
                flareEffStr = scanner.nextLine();
            } 
            if (flareEff >= 0.5 && flareEff <= 1.5){
                acceptableInput = true;
            } else {
                System.out.println("Syötä luku väliltä 0.5 - 1.5");
                flareEffStr = scanner.nextLine();
            }
        }
        
        
        acceptableInput = false;
        System.out.println("Syötä tähdenpurkauksen todennäköisyys (kerran kuudessa tunnissa)");
        String flareProbStr = scanner.nextLine();
        double flareProb = 0;
        while (!acceptableInput){
            try {
                flareEff = Double.parseDouble(flareProbStr); 
            } catch (NumberFormatException e) {
                System.out.println("Syötä liukuluku väliltä 0.0 - 1.0");
                flareProbStr = scanner.nextLine();
            } 
            if (flareProb >= 0.0 && flareProb <= 1.0){
                acceptableInput = true;
            } else {
                System.out.println("Syötä luku väliltä 0.0 - 1.0");
                flareProbStr = scanner.nextLine();
            }
        }
        
        
        
        sc.setupFunctionHandler("sine", amplitude, frequency);
        
        solPower.go();
        

       

        
     

    }

}

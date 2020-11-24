/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.rasmus.logic;
import java.util.Random;

/**
 *
 * @author Rasmus
 */
public class StarControl {

    int seed = 1000;
    Random random = new Random(seed);

    double baseluminosity;
    //This sets the power of a flare with 1.00 as the normal base luminosity
    double flareEfficient;
    //This is the propability of a flare in the default simulation time-step, which is currently and in foreseeable future 1 day
    double flarePropability;
    // This is the current date in days
    double phase;

    //Function handling defines the used function to compute daily power
    FunctionHandler functionHandler = new FunctionHandler();
    String function = "sine";

    // This is a list of daily powers computed or inserted in an array;
    double[] dailyPower;

    public StarControl() {
        baseluminosity = 1;
        flareEfficient = 1.01;
        flarePropability = 0.25;
        phase = 0.0;

    }

    public StarControl(double luminosity, double flareEff, double flareProb) {
        if (luminosity <= 0) {
            this.baseluminosity = 1;
            System.out.println("Tähden kirkkaus asetettiin oletusarvoon virheellisen syötteen takia");
        } else {
            this.baseluminosity = luminosity;
        }

        if (flareEff < 1) {
            this.flareEfficient = 1.00;
            System.out.println("Tähden kirkkaus ei voi pudota flaren takia");
        } else {
            this.flareEfficient = flareEff;
        }
        if (flareProb < 0 || flareProb > 1) {
            this.flarePropability = 0.25;
            System.out.println("Todennäköisyys asetettu arvoon 0.25 virheellisen syötteen takia");
        } else {
            this.flarePropability = flareProb;
        }
        phase = 0.0;

    }

    public void resetRandom() {
        this.random = new Random(seed);
    }

    public void setRandomSeed(int seed) {
        this.seed = seed;
    }

    public int getRandomSeed() {
        return this.seed;
    }

    public void setStats(double lum, double fleff, double flap) {
        if (lum <= 0) {
            this.baseluminosity = 1;
        } else {
            this.baseluminosity = lum;
        }
        if (fleff < 1) {
            this.flareEfficient = 1;
        } else {
            this.flareEfficient = fleff;
        }
        if (flap > 1 || flap < 0) {
            this.flarePropability = 0.25;
        } else {
            this.flarePropability = flap;
        }
    }

    public double getBaseluminosity() {
        return this.baseluminosity;

    }

    public double getFlareEfficient() {
        return this.flareEfficient;
    }

    public double getFlarePropability() {
        return this.flarePropability;
    }

    public int getAmountOfFlaresPerDay(double flareProb) {
        int number = 0;
        for (int i = 0; i < 4; i++) {
            double helperdbl = random.nextDouble();
            if (helperdbl < flareProb) {
                number++;
            }
        }
        return number;
    }

    public void addAllowedFunctionToHandler(String function) {
        functionHandler.addToAllowedFunctions(function);
    }

    public void setupFunctionHandler(String type, double amplitude, double frequency) {
        if (functionHandler.getAllowedFunctions().contains(type)) {

            if (amplitude >= 0 && frequency > 0) {
                functionHandler.setParameters(type, amplitude, frequency);
            }

        }

    }

    public double radiationPowerThisDay() {
        double helperdbl = (1 - functionHandler.getValue(phase));
        double flareNumber = getAmountOfFlaresPerDay(flarePropability);
        double flarePower = baseluminosity * flareNumber * (1 - flareEfficient);
        double totalPower = flarePower + (baseluminosity * helperdbl);
        return totalPower;
    }

    public void incrementPhase() {
        phase = phase + 1.0;
    }

    public void setPhase(double phase) {
        if (phase >= 0) {
            this.phase = phase;
        } else {
            phase = 0;
        }
    }

    public double returnPhase() {
        return this.phase;
    }

    public String returnFunctionHandlerStats() {
        return functionHandler.toString();
    }

}

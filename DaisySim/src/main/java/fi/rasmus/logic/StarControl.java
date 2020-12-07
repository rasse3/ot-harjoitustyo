package fi.rasmus.logic;

import java.util.Random;

import java.util.ArrayList;

/**
 * StarControl-class controls the star and its related values.
 *
 * @author Rasmus
 */
public class StarControl {

    int seed = 1000;
    Random random = new Random(seed);

    double orbitalDistance;
    //This defines the distance of the planet from the sun in AUs, ie. (average) distances of earth from sun
    double baseluminosity;
    //This sets the power of a flare with 1.00 as the normal base luminosity
    double flareEfficient;
    //This is the propability of a flare in the default simulation time-step, which is currently and in foreseeable future 1 day
    double flarePropability;
    // This is the current date in days
    double phase;
    // This is the energy flux of the Sun 
    double flux = 917;

    //Function handling defines the used function to compute daily power
    FunctionHandler functionHandler = new FunctionHandler();
    String function = "sine";

    // This is a list of daily powers computed or inserted in an array;
    double[] dailyPower;

    /**
     * This constructor builds a star with default values.
     */
    public StarControl() {
        baseluminosity = 1;
        flareEfficient = 1.01;
        flarePropability = 0.25;
        phase = 0.0;
        orbitalDistance = 149000000;

    }
    
    /**
     *  Sets the initial value of solar luminosity to be used by the logger.
     * @return The initial luminosity;
     */
    
        public double  setInitial(){
            return baseluminosity;
        }

    /**
     * This constructor builds a star with user-given values.
     *
     * @param luminosity The average luminosity of the star
     * @param flareEff The amount by which the flare multiplies the stars
     * luminosity
     * @param flareProb The propability of a flare per 6h period, with maximum
     * number of flares being 4 per day.
     */
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

    /**
     * This function resets the random-function to start over with the same
     * seed.
     */
    public void resetRandom() {
        this.random = new Random(seed);
    }

    /**
     * This function sets the random seed (you need to reset the random with
     * "resetRandom()" for this change to take effect).
     *
     * @param seed Seed to be given to the random function
     */
    public void setRandomSeed(int seed) {
        this.seed = seed;
    }

    /**
     * Returns the current random seed used by the propability functions.
     *
     * @return The seed of the random function
     */
    public int getRandomSeed() {
        return this.seed;
    }

    /**
     * Set the statistics for the star.
     *
     * @param lum The average luminosity of the star
     * @param fleff The amount by which the flare multiplies the stars
     * luminosity
     * @param flap The propability of a flare in a 6h period. Maximum number of
     * flares per day is 4
     */
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

    /**
     * Returns the basic luminosity of the star.
     *
     * @return The luminosity of the star
     */
    public double getBaseluminosity() {
        return this.baseluminosity;

    }

    /**
     * Returns the flare coefficient.
     *
     * @return The flare coefficient
     */
    public double getFlareEfficient() {
        return this.flareEfficient;
    }

    /**
     * Returns the flare propability per 6h.
     *
     * @return The flare propability
     */
    public double getFlarePropability() {
        return this.flarePropability;
    }

    /**
     * Randomizes the number of flares every day.
     *
     * @param flareProb The propability of a flare in 6h period
     * @return Number of flares for a given day
     */
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

   public void addAllowedFunctionToHandler(String function){
       functionHandler.addToAllowedFunctions(function);
   }
    
    
    /**
     * Returns allowed functions of the function handler.
     *
     * @return Allowed functions as list of strings
     */
    public ArrayList<String> returnAllowedFunctions() {
        return functionHandler.getAllowedFunctions();
    }

    /**
     * Sets up the function handler stats for a wave function.
     *
     * @param type Type of function
     * @param amplitude Amplitude of a wave function
     * @param frequency Frequency of a wave function
     */
    public void setupFunctionHandler(String type, double amplitude, double frequency) {
        if (functionHandler.getAllowedFunctions().contains(type)) {

            if (amplitude >= 0 && frequency > 0) {
                functionHandler.setParameters(type, amplitude, frequency);
            }

        }

    }

    /**
     * Returns the radiation power of the star per day (randomizes the number of
     * flares).
     *
     * @return Radiation power in relation to the suns average value
     */
    public double radiationPowerThisDay() {
        double helperdbl = (1 - functionHandler.getValue(phase));
        double flareNumber = getAmountOfFlaresPerDay(flarePropability);
        double flarePower = baseluminosity * flareNumber * (1 - flareEfficient);
        double totalPower = flarePower + (baseluminosity * helperdbl);
        return totalPower*flux
                ;
    }

    /**
     * Increments the phase (currently only complete days).
     */
    public void incrementPhase() {
        phase = phase + 1.0;
    }

    /**
     * Sets the phase to an arbitrary value.
     *
     * @param phase Phase (currently only possible to use days) to set
     */
    public void setPhase(double phase) {
        if (phase >= 0) {
            this.phase = phase;
        } else {
            phase = 0;
        }
    }

    /**
     * Returns the phase (day) of the simulation.
     *
     * @return Day number
     */
    public double returnPhase() {
        return this.phase;
    }

    /**
     * Returns the functions stats (if wave function, the amplitude and
     * frequency) as string.
     *
     * @return String with the function parameters
     */
    public String returnFunctionHandlerStats() {
        return functionHandler.toString();
    }

    /**
     * Sets orbital distance of planet from star. Currently all orbits are
     * circular ones.
     *
     * @param distance Distance (constant) to the star
     */
    public void setOrbitalDistance(double distance) {
        this.orbitalDistance = distance;
    }

    /**
     * Return (constant) orbital distance of planet from star.
     *
     * @return Orbital distance
     */
    public double getOrbitalDistance() {
        return orbitalDistance;

    }

}

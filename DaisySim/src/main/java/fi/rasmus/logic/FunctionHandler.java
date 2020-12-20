package fi.rasmus.logic;

import java.util.ArrayList;

// Handles the function used to vary the star's luminosity
public class FunctionHandler {

    String function;
    ArrayList<String> allowedFunctions = new ArrayList<>();
   

    double amplitude;
    double frequencyRadPerDay;

    /**
     * Constructor for the function handler object with default values (Sine is default function).
     */
    public FunctionHandler() {
        this.function = "sine";
        this.amplitude = 0.01;
        this.frequencyRadPerDay = 0.03;
        allowedFunctions.add("sine");

    }

    /**
     * Constructor for the function handler specifying the values.
     *
     * @param type          Name of the function
     * @param amplitude     Amplitude if using wave-functions
     * @param frequency     Frequency if using wave-functions
     * 
     */
    public FunctionHandler(String type, double amplitude, double frequency) {
        this.function = type;

        this.amplitude = amplitude;
        this.frequencyRadPerDay = frequency;
    }

    /**
     * Gets the function's value for a given day.
     *
     * @param day The time for which the value is wanted for
     * @return Returns the function value corresponding to the day
     */
    public double getValue(double day) {

        if (function.equals("sine")) {
            return (amplitude * Math.sin(frequencyRadPerDay * day));
        }

        return 0.0;

    }

    /**
     * '
     * Lists the functions available for the star.
     *
     * @return List of available functions
     */
    public ArrayList<String> getAllowedFunctions() {
        return allowedFunctions;
    }

    /**
     * Adds new functions to the list of allowed functions.
     *
     * @param entry The function to be added to the list of functions.
     */
    public void addToAllowedFunctions(String entry) {
        allowedFunctions.add(entry);
    }

    /**
     * Sets the parameters of a function.
     *
     * @param type Name of the function used.
     * @param amplitude Amplitude if using a wave-function
     * @param frequency Frequency if using a wave-function
     */
    public void setParameters(String type, double amplitude, double frequency) {
        this.function = type;

        this.amplitude = amplitude;
        this.frequencyRadPerDay = frequency;
    }

    /**
     * Returns the parameters of a wave function used to control the star.
     *
     * @return The amplitude and the frequency of the star's luminosity as a
     * string
     */
    public String toString() {
        return this.function + ": amplitude: " + amplitude + ", frequency: " + frequencyRadPerDay;
    }

}

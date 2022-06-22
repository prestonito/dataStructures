import java.util.*;
import java.math.*;
import java.lang.*;

/**
 * Implementation of a Guitar String using an internal ArrayList
 * 
 * Time Spent: 1hr 30min
 * 
 * @author Preston Ito
 * @author Liz Austell
 */

public class GuitarStringArrayList extends GuitarString {

    private ArrayList<Double> ringBuffer; // Models a string in which energy
                                          // travels back and forth
    public static double ENERGY_DECAY_FACTOR = 0.996;
    private int ticCounter = 0;

    /**
     * Constructs a new GuitarStringArrayList object
     * 
     * This constructor creates a GuitarStringArrayList and initializes
     * the internal ringBuffer to represent a guitar string at rest by
     * inputting a certain amount of zeros based on the inputted frequency.
     * 
     * @param frequency the frequency of the sound wave
     * @throws IllegalArgumentException if frequency is less than/equal to 0
     *                                  or if the ringBuffer ArrayList would have
     *                                  less than 2 elements
     */
    public GuitarStringArrayList(double frequency) throws IllegalArgumentException {
        if (frequency <= 0 || Math.round(StdAudio.SAMPLE_RATE / frequency) < 2)
            throw new IllegalArgumentException();

        ringBuffer = new ArrayList<Double>();

        for (int i = 0; i < Math.round(StdAudio.SAMPLE_RATE / frequency); i++) {
            ringBuffer.add(0.0);
        }

    }

    /**
     * Constructs a new GuitarStringArrayList object
     * 
     * 
     * This constructor creates a GuitarStringArrayList and initializes the
     * internal ringBuffer using values from the init double array.
     * 
     * 
     * @param init an array of double values representing values in
     *             the ringBuffer
     * @throws IllegalArgumentException if ringBuffer ArrayList would
     *                                  have less than 2 elements
     */
    public GuitarStringArrayList(double[] init) throws IllegalArgumentException {
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }

        ringBuffer = new ArrayList<Double>();

        for (int i = 0; i < init.length; i++) {
            ringBuffer.add(init[i]);
        }
    }

    /**
     * Replaces every element in ringBuffer with a random value
     * 
     * This method replaces every element in the internal ringBuffer with
     * random values between -0.5 inclusive and +0.5 exclusive.
     */
    public void pluck() {
        for (int i = 0; i < ringBuffer.size(); i++) {
            ringBuffer.set(i, Math.random() - 0.5);
        }
    }

    /**
     * Applies the Karplus-strong update once
     * 
     * This method deletes the sample at the front of the buffer
     * and adds to the end of the buffer the average of
     * the first two samples, multiplied by the energy
     * decay factor (0.996)
     */
    public void tic() {
        ringBuffer.add((ringBuffer.get(0) + ringBuffer.get(1)) * ENERGY_DECAY_FACTOR / 2);
        ringBuffer.remove(0);
        ticCounter++;
    }

    /**
     * Returns first value of ringBuffer
     * 
     * This method returns the current sample (the value at the
     * front of the ring buffer).
     * 
     * @return first value of ringBuffer
     */
    public double sample() {
        return ringBuffer.get(0);
    }

    /**
     * Returns a tic counter
     * 
     * This method returns the number of times that the tic method
     * has been called
     * 
     * @return ticCounter, a counter for the tics
     */
    public int time() {
        return ticCounter;
    }
}

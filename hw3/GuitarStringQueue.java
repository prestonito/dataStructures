import java.util.*;
import java.math.*;
import java.lang.*;

/**
 * Implementation of a Guitar String using an internal Queue
 * 
 * Time Spent: 1hr 30min
 * 
 * @author Preston Ito
 * @author Liz Austell
 */

public class GuitarStringQueue extends GuitarString {

    private Queue<Double> ringBuffer; // Models a string in which energy
                                      // travels back and forth
    public static double ENERGY_DECAY_FACTOR = 0.996;
    private int ticCounter = 0;

    /**
     * Constructs a new GuitarStringQueue object
     * 
     * This constructor creates a GuitarStringQueue and initializes
     * the internal ringBuffer to represent a guitar string at rest by
     * inputting a certain amount of zeros based on the inputted frequency.
     * 
     * @param frequency the frequency of the sound wave
     * @throws IllegalArgumentException if frequency is less than/equal to 0
     *                                  or if the ringBuffer Queue would have
     *                                  less than 2 elements
     */
    public GuitarStringQueue(double frequency) throws IllegalArgumentException {
        if (frequency <= 0 || Math.round(StdAudio.SAMPLE_RATE / frequency) < 2)
            throw new IllegalArgumentException();

        ringBuffer = new LinkedList<Double>();

        for (int i = 0; i < Math.round(StdAudio.SAMPLE_RATE / frequency); i++) {
            ringBuffer.add(0.0);

        }

    }

    /**
     * Constructs a new GuitarStringQueue object
     * 
     * 
     * This constructor creates a GuitarStringQueue and initializes the
     * internal ringBuffer using values from the init double array.
     * 
     * 
     * @param init an array of double values representing values in
     *             the ringBuffer
     * @throws IllegalArgumentException if ringBuffer Queue would
     *                                  have less than 2 elements
     */
    public GuitarStringQueue(double[] init) throws IllegalArgumentException {
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }

        ringBuffer = new LinkedList<Double>();

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
        int saveSize = ringBuffer.size();
        for (int i = 0; i < saveSize; i++) {
            ringBuffer.remove();
        }
        for (int i = 0; i < saveSize; i++) {
            ringBuffer.add(Math.random() - 0.5);
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
        double firstVal = ringBuffer.peek();
        ringBuffer.remove();
        double secondVal = ringBuffer.peek();
        ringBuffer.add((firstVal + secondVal) * ENERGY_DECAY_FACTOR / 2);
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

        return ringBuffer.peek();

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

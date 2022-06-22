import javax.swing.event.SwingPropertyChangeSupport;
import java.lang.*;

/**
 * Implementation of a 37 stringed Guitar instrument
 * 
 * Time Spent: 1hr 30min
 * 
 * @author Preston Ito
 * @author Liz Austell
 */
public class Guitar37 implements Guitar {
    private GuitarStringArrayList[] notes;

    // keyboard layout
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    /**
     * Creates a new Guitar37 object
     *
     * This constructor creates a new instance of the Guitar 37 object that
     * extends the Guitar class. It creates an array of GuitarStringArrayList
     * objects and assigns each GuitarStringArrayList to a frequency.
     */
    public Guitar37() {
        notes = new GuitarStringArrayList[37];
        for (int i = 0; i < 37; i++) {
            notes[i] = new GuitarStringArrayList(440.0 *
                    Math.pow(2, (double) (i - 24) / 12));

        }
    }

    /**
     * Returns true or false whether user entered valid key
     * 
     * This method returns true if the user-input key (string) exists
     * in String KEYBOARD and returns false if key
     * doesn't exist in String KEYBOARD.
     * 
     * @param string the key pressed by the user
     * @return true or false if string is a character in KEYBOARD
     */
    public boolean hasString(char string) {
        boolean flag = false;
        for (int i = 0; i < KEYBOARD.length(); i++) {
            if (string == KEYBOARD.charAt(i))

                flag = true;

        }
        return flag;
    }

    /**
     * Plucks input string if inputted key is valid
     * 
     * This method plucks the inputted string as long as it is a valid note
     * on the keyboard.
     *
     * @param string the key pressed by the user
     * @throws IllegalArgumentException if the character inputted is not
     *                                  a valid key on the keyboard.
     */
    public void pluck(char string) throws IllegalArgumentException {

        boolean flag = false;
        for (int i = 0; i < KEYBOARD.length(); i++) {
            if (string == KEYBOARD.charAt(i)) {
                notes[i].pluck();
                flag = true;
            }

        }
        if (!flag)
            throw new IllegalArgumentException();
    }

    /**
     * Sends superposition of samples to sound card
     * 
     * This method computes the superposition of samples by adding together
     * each string on the Guitar. It then sends the result to the sound card
     * through StdAudio.
     */
    public void play() {
        // compute the superposition of samples
        double sample = 0;
        for (int i = 0; i < KEYBOARD.length(); i++) {
            sample += notes[i].sample();
        }
        // send the result to the sound card
        StdAudio.play(sample);
    }

    /**
     * Calls method tic for each string
     * 
     * This method calls the tic method on each of the 37
     * GuitarStringArrayList objects in the keyboard.
     */
    public void tic() {
        for (int i = 0; i < KEYBOARD.length(); i++) {
            notes[i].tic();
        }
    }

}
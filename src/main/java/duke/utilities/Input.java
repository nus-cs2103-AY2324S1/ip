package duke.utilities;

/**
 * Deals with storing input data
 */
public class Input {

	/** Variable to store the command section of the input string */
    private String command;

	/** Variable to store the full input */
    private String fullInput;

	/** Variable to store the number of words inside the input */
    private int wordLength;

    /**
     * Creates a new instance of an Input object
     *
     * @param command The name of the command to be called
     * @param fullInput The full String input typed in by the user
     * @param wordLength The number of words in the input
     */
    public Input(String command, String fullInput, int wordLength) {
        this.command = command;
        this.fullInput = fullInput;
        this.wordLength = wordLength;
    }

    public String getCommand() {
        return this.command;
    }

    public int getLength() {
        return this.wordLength;
    }

    public String getFullInput() {
        return this.fullInput;
    }
}

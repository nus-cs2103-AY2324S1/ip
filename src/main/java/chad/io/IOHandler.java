package chad.io;

/**
 * Interface for handling input and output operations.
 */
public interface IOHandler {
    /**
     * Reads input from the user.
     * @return the user's input as a string.
     */
    String readInput();

    /**
     * Writes output to the user.
     * @param output the string to be outputted.
     */
    void writeOutput(String output);

    /**
     * Greets the user upon startup.
     */
    void greet();

    /**
     * Bids farewell to the user upon exiting.
     */
    void sayGoodbye();
}

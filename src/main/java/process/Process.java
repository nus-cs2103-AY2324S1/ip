package process;

/**
 * An interface to define the behavior of a process in general
 */
public interface Process {
    /**
     * Takes in a user input, validates it and processes the input in accordance to the respective task behavior.
     * Then finally returns a string message response to be displayed for the user
     * @param input String message captured by the user
     * @return String message of appropriate response for the user input
     */
    public String processInput(String input);
}

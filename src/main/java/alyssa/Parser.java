package alyssa;

/**
 * The Parser class processes commands.
 */
public class Parser {
    /**
     * Splits a command by whitespaces, to a maximum of 2 strings.
     * @param command The raw command provided on user input.
     * @return An array of strings containing the split command, of length at most 2.
     */
    protected String[] parseCommand(String command) {
        return command.split(" ", 2);
    }
    /**
     * Splits a stored task by ~, to a maximum of 5 strings.
     * @param storedTask The task to be parsed.
     * @return The parsed task, in the form of a String array of length at most 5.
     */
    protected String[] parseStoredTask(String storedTask) {
        return storedTask.split(" ~ ", 5);
    }
}

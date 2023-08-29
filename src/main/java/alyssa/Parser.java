package alyssa;

/**
 * The Parser class processes commands.
 */
public class Parser {
    protected String[] parseCommand(String command) {
        return command.split(" ", 2);
    }
    protected String[] parseStoredTask(String storedTask) {
        return storedTask.split(" ~ ", 5);
    }
}

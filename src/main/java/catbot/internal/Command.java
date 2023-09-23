package catbot.internal;

/**
 * A functional interface for delayed and dynamic execution of commands.
 */
public interface Command {

    /**
     * Runs command.
     * @param args string representing arguments to pass to the command.
     */
    void run(String args);
}

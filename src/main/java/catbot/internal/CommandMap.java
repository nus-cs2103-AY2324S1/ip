package catbot.internal;

import java.util.HashMap;

/**
 * Object to store commands as key-value pairs, intended for use specifically with text-triggered functionality.
 * Key is intended to be a String used to invoke its corresponding functionality.
 * Value is a Command, which is a functional interface.
 */
public class CommandMap {

    private final HashMap<String, Command> commandMap = new HashMap<>();
    private Command defaultCommand;
    //endregion

    /**
     * Adds an invocation-command pair to the map. Supports pipelining.
     *
     * @param invocation the String that calls the command.
     * @param lambda the Command that triggers through the corresponding String.
     * @return this, for pipelining
     */
    public CommandMap addCommand(String invocation, Command lambda) {
        commandMap.put(invocation, lambda);
        return this;
    }

    /**
     * Initializes or replaces the default command that runs when an invocation does not match any command.
     *
     * @param defaultCommand Command to run.
     * @return this, for pipelining.
     */
    public CommandMap setDefaultCommand(Command defaultCommand) {
        this.defaultCommand = defaultCommand;
        return this;
    }

    /**
     * Runs the command corresponding to the invocation, passing it a String argument.
     *
     * @param invocation String to use as the key value to retrieve the relevant Command.
     * @param argument String to pass as an argument to the Command.
     */
    public void run(String invocation, String argument) {
        if (commandMap.containsKey(invocation)) {
            commandMap.get(invocation).run(argument);
        } else if (defaultCommand != null) {
            defaultCommand.run(invocation);
        }
    }
}

package deterministicparrot;

import java.util.HashMap;
import java.util.Map;

/**
 * Parser class for handling user commands in the Deterministic Parrot application.
 */
public class Parser {

    /**
     * Functional interface for a checked consumer that can throw exceptions.
     *
     * @param <T> The type of input to the consumer.
     */
    @FunctionalInterface
    interface CheckedConsumer<T> {
        void accept(T t) throws Exception;
    }

    private Map<String, CheckedConsumer<String[]>> commandHandlers = new HashMap<>();

    /**
     * Registers a command handler for a specific command.
     *
     * @param command The command to be registered.
     * @param handler The command handler function.
     */
    public void registerHandler(String command, CheckedConsumer<String[]> handler) {
        this.commandHandlers.put(command, handler);
    }

    /**
     * Handles a user command by invoking the appropriate command handler.
     *
     * @param input The user input containing the command and arguments.
     * @throws Exception If an error occurs while handling the command.
     */
    public void handleCommand(String input) throws Exception {
        String[] tokens = input.split(" ");
        CheckedConsumer<String[]> cmdHandler = this.commandHandlers.get(tokens[0]);
        if (cmdHandler != null) {
            cmdHandler.accept(tokens);
        } else {
            throw new DeterministicParrotException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

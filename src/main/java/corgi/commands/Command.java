package corgi.commands;

import java.util.Stack;

import corgi.State;
import javafx.util.Pair;

/**
 * An abstract class to represent command.
 */
public abstract class Command {
    /**
     * A flag indicating whether this command should exit the application.
     */
    private boolean isExit;

    /**
     * Initializes a new Command instance with the specified exit flag and command type.
     *
     * @param isExit The flag indicating whether this command should exit the application.
     * @param type The type of command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command, performing its intended action on the provided task list,
     * text renderer, and storage. Returns new state and string message
     *
     * @param currState The current state of the application.
     * @param history The history stack to store the states.
     * @return A pair containing the new state and a string message indicating the result of the command execution.
     * @throws CommandExecutionException If an error occurs during command execution.
     */
    public abstract Pair<State, String> execute(State currState, Stack<Pair<State, Command>> history)
            throws CommandExecutionException;

    /**
     * Checks whether this command should exit the application.
     *
     * @return True if this command should exit, else false.
     */
    public boolean isExit() {
        return this.isExit;
    }
}

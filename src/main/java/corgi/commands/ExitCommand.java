package corgi.commands;

import java.util.Stack;

import corgi.State;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to exit the application.
 * This command terminates the application and provides an exit message to the user.
 */
public class ExitCommand extends Command {

    /**
     * Initializes a new ExitCommand instance.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the command by return an exit message, indicating that the application is terminating.
     *
     * @param currState The current state of the application.
     * @param history The history stack to store the states.
     * @return A pair containing the new state and a string message indicating the result of the command execution.
     */
    @Override
    public Pair<State, String> execute(State currState, Stack<Pair<State, Command>> history) {
        TextRenderer renderer = currState.getTextRenderer();

        String returnMsg = renderer.showExitMsg();

        return new Pair<>(currState, returnMsg);
    }
}

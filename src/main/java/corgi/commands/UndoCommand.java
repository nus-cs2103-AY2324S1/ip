package corgi.commands;

import java.util.Stack;

import corgi.State;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to undo previous command action.
 */
public class UndoCommand extends Command {
    /**
     * Initializes a new UndoCommand instance.
     */
    public UndoCommand() {
        super(false);
    }

    /**
     * Executes the command by reverting the previous action based on the provided
     * history stack and updating the task list accordingly.
     *
     * @param currState The current state of the application.
     * @param history The history stack to store the states.
     * @return A pair containing the new state and a string message indicating the result of the command execution.
     */
    @Override
    public Pair<State, String> execute(State currState, Stack<Pair<State, Command>> history)
            throws CommandExecutionException {
        if (history.isEmpty()) {
            throw new CommandExecutionException("Nothing to undo!");
        }

        Pair<State, Command> previous = history.pop();
        State prevState = previous.getKey();
        Command prevCommand = previous.getValue();

        prevState.save();

        TextRenderer renderer = prevState.getTextRenderer();

        String returnMsg = renderer.showUndoSucceed(prevCommand.toString());

        return new Pair<>(prevState, returnMsg);
    }
}

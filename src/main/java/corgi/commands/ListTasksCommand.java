package corgi.commands;

import java.util.Stack;

import corgi.State;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to list tasks in the task list.
 * This command retrieves and displays the list of tasks to the user.
 */
public class ListTasksCommand extends Command {
    /**
     * Initializes a new ListTasksCommand instance.
     */
    public ListTasksCommand() {
        super(false);
    }

    /**
     * Executes the command by retrieving and displaying the list of tasks to the user.
     * It returns either the list of tasks or a message indicating that no tasks are in the list.
     *
     * @param currState The current state of the application.
     * @param history The history stack to store the states.
     * @return A pair containing the new state and a string message indicating the result of the command execution.
     */
    @Override
    public Pair<State, String> execute(State currState, Stack<Pair<State, Command>> history) {
        TaskList currList = currState.getTaskList();
        TextRenderer currRenderer = currState.getTextRenderer();

        String returnMsg = currList.isEmpty()
                ? currRenderer.showNoTaskFound()
                : currRenderer.showTaskList(currList.toString());

        return new Pair<>(currState, returnMsg);
    }
}

package corgi.commands;

import java.util.Stack;

import corgi.State;
import corgi.tasks.TaskList;
import corgi.tasks.TaskListIndexOutOfBoundsException;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to delete a task from the task list.
 * This command deletes a task at the specified index from the task list.
 */
public class DeleteTaskCommand extends Command {
    /**
     * The index of the task to be deleted.
     */
    private int targetIdx;

    /**
     * Initializes a new DeleteTaskCommand instance with the specified target index.
     *
     * @param targetIdx The index of the task to be deleted.
     */
    public DeleteTaskCommand(int targetIdx) {
        super(false);
        this.targetIdx = targetIdx;
    }

    /**
     * Executes the command by deleting the task at the specified index from the task list,
     * saving the updated list to storage, store the state to history stack.
     *
     * @param currState The current state of the application.
     * @param history The history stack to store the states.
     * @return A pair containing the new state and a string message indicating the result of the command execution.
     * @throws CommandExecutionException If an error occurs during command execution.
     */
    @Override
    public Pair<State, String> execute(State currState, Stack<Pair<State, Command>> history)
            throws CommandExecutionException {
        try {
            history.push(new Pair<>(currState, this));

            TaskList currList = currState.getTaskList();
            String targetTaskInfo = currList.getTaskInfo(targetIdx);

            State newState = currState.removeTask(targetIdx);

            TextRenderer renderer = newState.getTextRenderer();
            TaskList list = newState.getTaskList();

            String returnMsg = renderer.showTaskDeleted(targetTaskInfo, list.size());

            return new Pair<>(newState, returnMsg);
        } catch (TaskListIndexOutOfBoundsException e) {
            throw new CommandExecutionException("Invalid index provided!");
        }
    }

    @Override
    public String toString() {
        return "Delete task " + (this.targetIdx + 1);
    }
}

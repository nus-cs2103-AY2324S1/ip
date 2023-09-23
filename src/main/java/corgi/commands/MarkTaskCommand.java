package corgi.commands;

import java.util.Stack;

import corgi.State;
import corgi.tasks.TaskList;
import corgi.tasks.TaskListIndexOutOfBoundsException;
import corgi.tasks.TaskStatusException;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to mark a task as done or undone in the task list.
 * This command updates the status of a task at the specified index in the task list.
 */
public class MarkTaskCommand extends Command {
    /**
     * The index of the task to be marked.
     */
    private int index;

    /**
     * The new status of the task (true for done, false for undone).
     */
    private boolean isDone;

    /**
     * Initializes a new MarkTaskCommand instance with the specified index, status, and command type.
     *
     * @param index The index of the task to be marked.
     * @param isDone The new status of the task (true for done, false for undone).
     * @param type The type of command (CommandType.MARK_DONE or CommandType.MARK_UNDONE).
     */
    public MarkTaskCommand(int index, boolean isDone) {
        super(false);
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Executes the command by marking the task at the specified index with the new status,
     * saving the updated list to storage.
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

            State newState = currState.markTask(this.index, this.isDone);

            TextRenderer renderer = newState.getTextRenderer();
            TaskList list = newState.getTaskList();

            String returnMsg = (isDone)
                    ? renderer.showTaskDone(list.getTaskInfo(this.index))
                    : renderer.showTaskUndone(list.getTaskInfo(this.index));

            return new Pair<>(newState, returnMsg);
        } catch (TaskListIndexOutOfBoundsException e) {
            throw new CommandExecutionException("Invalid index provided!");
        } catch (TaskStatusException e) {
            throw new CommandExecutionException("The task is already in that status!");
        }
    }

    @Override
    public String toString() {
        String action = this.isDone ? "Mark" : "Unmark";
        return action + " task " + (this.index + 1);
    }
}

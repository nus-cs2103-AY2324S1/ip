package duke.commands;

import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;
import duke.io.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from a TaskList.
 */
public class DeleteCommand extends Command {
    private final int indexToDelete;

    /**
     * Initializes a new DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        indexToDelete = index;
    }

    /**
     * Checks if the provided index is a valid index in the TaskList.
     *
     * @param list The list of tasks.
     * @return true if the index is valid, false otherwise.
     */
    private boolean isValidIndex(TaskList list) {
        return this.indexToDelete >= 0 && indexToDelete < list.getNumberOfTasks();
    }

    /**
     * Executes the delete operation. Removes the task from the TaskList, updates
     * the UI with the deletion message, and saves the state to storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface used to display messages.
     * @param storage The storage used to save tasks.
     * @throws Exception If there is any error during the execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            checkForEmptyTasks(tasks);
            validateTaskIndex(tasks);

            Task deletedTask = tasks.removeTask(indexToDelete);
            String format = "Noted. I've removed this task:\n %s \n" + "Now you have %d tasks in the list.";
            String message = String.format(format, deletedTask.toString(), tasks.getNumberOfTasks());
            ui.addToResponse(message);
        } finally {
            storage.save(tasks);
        }
    }

    private void checkForEmptyTasks(TaskList tasks) throws UnknownCommandException {
        if (tasks.isEmpty()) {
            String errorMessage = ErrorMessages.TASK_LIST_EMPTY_ERROR + ErrorMessages.ADD_TASKS;
            throw new UnknownCommandException(errorMessage);
        }
    }

    private void validateTaskIndex(TaskList tasks) throws UnknownCommandException {
        if (!isValidIndex(tasks)) {
            String errorMessage = String.format(ErrorMessages.INVALID_INDEX_ERROR, tasks.getNumberOfTasks());
            throw new UnknownCommandException(errorMessage);
        }
    }
}

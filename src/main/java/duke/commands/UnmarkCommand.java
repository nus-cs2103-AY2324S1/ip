package duke.commands;

import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;
import duke.io.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task as done in the task list.
 */
public class UnmarkCommand extends Command {
    private final int indexToUnmark;

    /**
     * Creates an instance of the UnmarkCommand with the specified index to unmark.
     *
     * @param index The index of the task in the task list to be unmarked.
     */
    public UnmarkCommand(int index) {
        indexToUnmark = index;
    }

    /**
     * Checks if the given index is a valid index in the task list.
     *
     * @param list The task list to be checked against.
     * @return true if the index is valid, false otherwise.
     */
    public boolean isValidIndex(TaskList list) {
        return indexToUnmark >= 0 && indexToUnmark < list.getNumberOfTasks();
    }

    /**
     * Executes the unmark command, unmarking the task at the specified index.
     * Also ensures that changes are saved even if an exception occurs.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI component responsible for user interactions.
     * @param storage The storage component responsible for saving tasks.
     * @throws Exception If there are issues in the execution, e.g., invalid index or empty tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        try {
            checkForEmptyTasks(tasks);
            validateTaskIndex(tasks);

            Task currentTask = tasks.getTask(indexToUnmark);
            currentTask.unmark();
            String message = "OK, I've marked this task as not done yet:\n " + currentTask;
            ui.addToResponse(message);
        } finally {
            storage.save(tasks);
        }
    }

    /**
     * Checks if the task list is empty and throws an exception if it is.
     *
     * @param tasks The list of tasks to be checked.
     * @throws UnknownCommandException If the task list is empty.
     */
    private void checkForEmptyTasks(TaskList tasks) throws UnknownCommandException {
        if (tasks.isEmpty()) {
            String errorMessage = ErrorMessages.TASK_LIST_EMPTY_ERROR + ErrorMessages.ADD_TASKS;
            throw new UnknownCommandException(errorMessage);
        }
    }

    /**
     * Validates the task index and throws an exception if it's not a valid index.
     *
     * @param tasks The list of tasks to be checked against.
     * @throws UnknownCommandException If the index is invalid.
     */
    private void validateTaskIndex(TaskList tasks) throws UnknownCommandException {
        if (!isValidIndex(tasks)) {
            String errorMessage = String.format(ErrorMessages.INVALID_INDEX_ERROR, tasks.getNumberOfTasks());
            throw new UnknownCommandException(errorMessage);
        }
    }
}


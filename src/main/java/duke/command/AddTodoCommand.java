package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents a command to add a todo task.
 * Inherits from the Command class.
 */
public class AddTodoCommand extends Command {

    /** The description of the todo to add */
    private final String description;

    /**
     * Constructs an AddTodoCommand object with the given description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the AddTodoCommand by adding the todo task to the task list.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage component.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Create the task first.
        ToDo todo = new ToDo(description);

        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        // Check for duplicates before adding the task.
        if (storage.isDuplicateTask(tasks.getTasks(), todo)) {
            throw new DukeException("This task already exists.");
        }

        tasks.addTask(todo);
        ui.showTaskAddedMessage(tasks);
    }

    /**
     * Returns a boolean indicating whether the command is an exit command.
     *
     * @return A boolean indicating whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

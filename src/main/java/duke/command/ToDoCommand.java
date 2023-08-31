package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import duke.exception.DukeException;
import duke.task.ToDo;

/**
 * Represents a command to add a "Todo" task.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Constructs a ToDoCommand with the given description.
     *
     * @param description The description of the "To-Do" task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the ToDoCommand by adding a new "To-Do" task to the task list,
     * displaying a completion message, and saving the tasks to storage.
     *
     * @param taskList The list of tasks to operate on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     * @throws DukeException If there's an error during command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        ui.displayCompletionMessage(todo, taskList.size());
        storage.saveTasksToFile(taskList);
    }

}

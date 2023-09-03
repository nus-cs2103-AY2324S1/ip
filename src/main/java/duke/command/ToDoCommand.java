package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents a command to create a ToDo object.
 */
public class ToDoCommand extends Command {
    /** Represents a ToDo object. */
    private final ToDo todo;

    /**
     * Constructor method.
     *
     * @param input User input.
     * @throws DukeException If any error occurs.
     */
    public ToDoCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.todo = new ToDo(input.strip());
    }

    /**
     * {@inheritDoc}
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(todo);
        ui.showMessage(String.format("Got it. I've added this task:\n    "
                + "%s\nNow you have %d tasks in the list.", todo, taskList.getListSize()));
        storage.appendFile(todo);
    }

    @Override
    public String executeGui(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(todo);
        storage.appendFile(todo);
        return String.format("Got it. I've added this task:\n    "
                + "%s\nNow you have %d tasks in the list.", todo, taskList.getListSize());
    }
}

package linus.command;

import linus.exception.LinusException;
import linus.task.TaskList;
import linus.task.ToDo;
import linus.util.Ui;

/**
 * Represents a command that adds a todo task to the task list.
 */
public class ToDoCommand extends Command {

    private static final String TODO_INCORRECT_FORMAT =
            "☹ OOPS!!! The description of a todo cannot be empty.";
    private TaskList tasks = null;
    private String data = "";
    private Ui ui = null;

    /**
     * Constructor for ToDoCommand.
     * @param tasks
     * @param data
     * @param ui
     */
    public ToDoCommand(TaskList tasks, String data, Ui ui) {
        this.tasks = tasks;
        this.data = data;
        this.ui = ui;
    }

    /**
     * Adds a todo task to the task list.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        if (data == "") {
            throw new LinusException(TODO_INCORRECT_FORMAT);
        }
        tasks.add(new ToDo(data));
        int tasksSize = tasks.getSize();
        ui.printAddSuccessMessage(tasks.get(tasksSize - 1), tasksSize);
    }
}

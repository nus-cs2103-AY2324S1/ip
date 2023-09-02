package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Represents a command that adds a todo task.
 */
public class ToDoCommand extends Command {

    private String description;

    /**
     * Constructs a ToDoCommand object using the superclass constructor and
     * initialises the description.
     *
     * @param description The description of the todo task.
     */
    public ToDoCommand(String description) {
        super(CommandType.TODO);
        this.description = description;
    }

    /**
     * Adds a todo task to the task list and displays the todo task message.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     */
    public void execute(TaskList tasks, Ui ui) {
        Task task = new ToDo(description);
        tasks.addTask(task);
        ui.showTodoMessage(task);
        ui.showTaskListSizeMessage(tasks.getSize(), true);
    }
}

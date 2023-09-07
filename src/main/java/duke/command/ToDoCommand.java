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
     * @return The response to the user input.
     */
    public String execute(TaskList tasks, Ui ui) {
        Task task = new ToDo(description);
        tasks.addTask(task);
        return ui.showTodoMessage(task) + "\n" +
                ui.showTaskListSizeMessage(tasks.getSize(), true);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDoCommand) {
            ToDoCommand todoCommand = (ToDoCommand) obj;
            return todoCommand.description.equals(this.description);
        }
        return false;
    }
}

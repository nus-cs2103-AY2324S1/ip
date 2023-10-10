package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.Task;
import gudetama.tasks.TaskList;
import gudetama.tasks.Todo;
import gudetama.ui.Ui;

/**
 * Represents a command to add a todo task to the task list
 */
public class TodoCommand extends Command {
    /**
     * Description of the todo task to be added
     */
    String description;

    /**
     * Constructor for TodoCommand
     * @param description Description of the todo task to be added
     */
    public TodoCommand(String description) {

        this.description = description;
    }

    /**
     * Executes the command that adds the todo task
     * @param tasksList Task list which contains the tasks
     * @param ui A UI instance that displays a message to indicate to the user the todo task has been added
     * @param storage Storage instance that represents the storage of the file
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        Task todo = new Todo(description);
        tasksList.addTask(todo);
        return ui.showAddedTask(tasksList);
    }
}

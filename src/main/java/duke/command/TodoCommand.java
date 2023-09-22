package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the program to create a new task.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructor for the class TodoCommand.
     * @param description Description of the task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }
    /**
     * Creates a new task and stores it in tasks,
     * shows appropriate response to user and saves task to storage.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task todo = new Task(this.description);
            tasks.addTask(todo);
            storage.saveList(tasks);
            return ui.addTaskResponse(todo, tasks);
        } catch (ChatException e) {
            return ui.showLoadingError(e);
        }
    };
}

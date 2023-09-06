package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the programme to create a new task.
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
     * @param tasks List of task stored by the programme.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task todo = new Task(this.description);
            tasks.addTask(todo);
            ui.formatTaskResponse(todo, tasks);
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    /**
     * Checks if command will end programme.
     * @return False.
     */
    public boolean isExit() {
        return false;
    };
}

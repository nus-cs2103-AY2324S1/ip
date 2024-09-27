package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the program to delete a task that has been saved.
 */
public class DeleteCommand extends Command {
    private String[] taskNumbers;

    /**
     * Constructor for the class DeleteCommand.
     * @param taskNumbers Index of the task in the saved list to be deleted.
     */
    public DeleteCommand(String[] taskNumbers) {
        this.taskNumbers = taskNumbers;
    }
    /**
     * Deletes the specified task from tasks,
     * shows appropriate response to user and updates tasks in storage.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList deletedList = tasks.deleteTask(taskNumbers);
            storage.saveList(tasks);
            return ui.deleteTaskResponse(deletedList, tasks);

        } catch (ChatException e) {
            return ui.showLoadingError(e);
        }
    }
}

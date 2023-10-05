package buddy.commands;

import buddy.Task;
import buddy.TaskList;
import buddy.exceptions.BuddyCommandException;
import buddy.exceptions.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * Represents a command to update fields in a task.
 */
public class UpdateDescriptionCommand extends Command {
    public static final String MESSAGE_FORMAT =
            "description: update <task index> /desc <description>\n"
            + "Example: update 2 /desc family trip\n";
    private int taskIndex;
    private String fieldToUpdate;
    private String update;

    /**
     * The constructor for an UpdateDescriptionCommand.
     *
     * @param taskIndex The index of the task to be updated
     * @param update The update to be made to the description
     */
    public UpdateDescriptionCommand(int taskIndex, String update) {
        this.taskIndex = taskIndex - 1;
        this.update = update;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToUpdate = tasks.getTask(taskIndex);
        taskToUpdate.updateTaskDescription(update);
        storage.writeToFile(tasks.getAllTasks());
        String response = ui.printUpdateSuccessMessage(taskToUpdate);
        return response;
    }
}

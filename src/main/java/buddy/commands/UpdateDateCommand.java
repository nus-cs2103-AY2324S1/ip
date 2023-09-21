package buddy.commands;

import java.time.LocalDate;

import buddy.Deadline;
import buddy.Event;
import buddy.Task;
import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * Represents a command to update fields in a task.
 */
public class UpdateDateCommand extends Command {
    private int taskIndex;
    private String fieldToUpdate;
    private LocalDate newDate;

    /**
     * The constructor for an UpdateDateCommand.
     *
     * @param taskIndex The index of the task to be updated
     * @param fieldToUpdate The field to be updated
     * @param newDate The new date to be updated
     */
    public UpdateDateCommand(int taskIndex, String fieldToUpdate, LocalDate newDate) {
        this.taskIndex = taskIndex - 1;
        this.fieldToUpdate = fieldToUpdate;
        this.newDate = newDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        Task taskToUpdate = tasks.getTask(taskIndex);
        if (taskToUpdate instanceof Deadline && fieldToUpdate.equalsIgnoreCase("by")
                || taskToUpdate instanceof Event && fieldToUpdate.equalsIgnoreCase("from")
                || taskToUpdate instanceof Event && fieldToUpdate.equalsIgnoreCase("to")) {
            taskToUpdate.updateDate(fieldToUpdate, newDate);
            storage.writeToFile(tasks.getAllTasks());
            String response = ui.printUpdateSuccessMessage(taskToUpdate);
            return response;
        } else {
            throw new BuddyException("Please ensure that the format is correct.");
        }
    }
}

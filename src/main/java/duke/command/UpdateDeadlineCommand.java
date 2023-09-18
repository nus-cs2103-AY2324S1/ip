package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command of updating a deadline task.
 */
public class UpdateDeadlineCommand extends UpdateCommand {

    /**
     * Constructor for the UpdateDeadlineCommand if only description is to be updated.
     * 
     * @param index Index of the deadline to be updated.
     * @param description Description to be updated with.
     */
    public UpdateDeadlineCommand(int index, String description) {
        super(index + 1, description);
    }

    /**
     * Constructor for the UpdateDeadlineCommand if only date is to be updated.
     * 
     * @param index Index of the deadline to be updated.
     * @param date Date to be updated with.
     */
    public UpdateDeadlineCommand(int index, LocalDateTime date) {
        super(index + 1, date);
    }

    /**
     * Constructor for the UpdateDeadlineCommand if description and date are to be updated.
     * 
     * @param index Index of the deadline to update.
     * @param description Description to be updated with.
     * @param date Date to be updated with.
     */
    public UpdateDeadlineCommand(int index, String description, LocalDateTime date) {
        super(index + 1, description, date, false);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task currTask = taskList.get(this.index);
        if (this.description != null) {
            if (this.firstDate != null) {
                currTask.update(description);
                currTask.updateDate(firstDate, false);
            } else {
                currTask.update(description);
            }
        } else {
            currTask.updateDate(firstDate, false);
        }
        ui.updateSuccess(currTask);
        storage.saveList(taskList.getAllTasks());
    }
}

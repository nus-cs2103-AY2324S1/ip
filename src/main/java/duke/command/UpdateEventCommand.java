package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command of updating an event task.
 */
public class UpdateEventCommand extends UpdateCommand {

    /**
     * Constructor for UpdateEventCommand if only the description is to be updated.
     * 
     * @param index Index of the event to update.
     * @param description Description to be updated with.
     */
    public UpdateEventCommand(int index, String description) {
        super(index + 1, description);
    }

    /**
     * Constructor for UpdateEventCommand if only the time is to be updated.
     * 
     * @param index Index of the event ot update.
     * @param time Date and time to be updated with.
     * @param isFirst Boolean value to indicate if the date to update is the first or not.
     */
    public UpdateEventCommand(int index, LocalDateTime time, boolean isFirst) {
        super(index + 1, time, isFirst);
    }

    /**
     * Constructor for UpdateEventCommand if both times are to be updated.
     * 
     * @param index Index of the event to update.
     * @param firstDate First date to be updated with.
     * @param secondDate Second date to be updated with.
     */
    public UpdateEventCommand(int index, LocalDateTime firstDate, LocalDateTime secondDate) {
        super(index + 1, firstDate, secondDate);
    }

    /**
     * Constructor for UpdateEventCommand if description and one date are to be updated.
     * 
     * @param index Index of the event to update.
     * @param description Description to be updated with.
     * @param time Date to be updated with.
     * @param isFirst Boolean value to indicate if date to update is the first date.
     */
    public UpdateEventCommand(int index, String description, LocalDateTime time, boolean isFirst) {
        super(index + 1, description, time, isFirst);
    }

    /**
     * Constructor for UpdateEventCommand if description and dates are to be updated.
     * 
     * @param index Index of the event to update.
     * @param description Description to be updated with.
     * @param firstDate First date to be updated with.
     * @param secondDate Second date to be updated with.
     */
    public UpdateEventCommand(int index, String description, LocalDateTime firstDate, LocalDateTime secondDate) {
        super(index + 1, description, firstDate, secondDate);
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task currTask = taskList.get(index);
        if (this.description != null) {
            if (this.firstDate != null) {
                if (this.secondDate != null) {
                    currTask.update(description);
                    currTask.updateDate(firstDate, true);
                    currTask.updateDate(secondDate, false);
                } else {
                    currTask.update(description);
                    currTask.updateDate(firstDate, isFirst);
                } 
            } else {
                currTask.update(description);
            }
        } else {
            if (this.firstDate != null) {
                if (this.secondDate != null) {
                    currTask.updateDate(firstDate, true);
                    currTask.updateDate(secondDate, false);
                } else {
                    currTask.updateDate(firstDate, isFirst);
                }
            }
        }
        ui.updateSuccess(currTask);
        storage.saveList(taskList.getAllTasks());
    }
}

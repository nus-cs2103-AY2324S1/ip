package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UpdateEventCommand extends UpdateCommand {

    public UpdateEventCommand(int index, String description) {
        super(index + 1, description);
    }

    public UpdateEventCommand(int index, LocalDateTime time, boolean isFirst) {
        super(index + 1, time, isFirst);
    }

    public UpdateEventCommand(int index, LocalDateTime firstDate, LocalDateTime secondDate) {
        super(index + 1, firstDate, secondDate);
    }

    public UpdateEventCommand(int index, String description, LocalDateTime time, boolean isFirst) {
        super(index + 1, description, time, isFirst);
    }

    public UpdateEventCommand(int index, String description, LocalDateTime firstDate, LocalDateTime secondDate) {
        super(index, description, firstDate, secondDate);
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

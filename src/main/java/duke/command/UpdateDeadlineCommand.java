package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UpdateDeadlineCommand extends UpdateCommand {

    public UpdateDeadlineCommand(int index, String description) {
        super(index + 1, description);
    }

    public UpdateDeadlineCommand(int index, LocalDateTime date) {
        super(index + 1, date);
    }

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

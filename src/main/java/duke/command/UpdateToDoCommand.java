package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class UpdateToDoCommand extends UpdateCommand {

    public UpdateToDoCommand(int index, String changeToBeMade) {
        super(index + 1, changeToBeMade);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task currTask = taskList.get(this.index);
        currTask.update(description);
        ui.updateSuccess(currTask);
        storage.saveList(taskList.getAllTasks());
    }
}


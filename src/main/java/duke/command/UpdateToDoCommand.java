package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

/**
 * Represents the command of updating a todo task.
 */
public class UpdateToDoCommand extends UpdateCommand {

    /**
     * Constructor for UpdateToDoCommand.
     * 
     * @param index Index of the todo to update.
     * @param changeToBeMade Description to be updated with.
     */
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


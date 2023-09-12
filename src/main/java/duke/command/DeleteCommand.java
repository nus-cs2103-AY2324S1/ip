package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        assert taskList != null: "Task list should not be null";
        assert uiManager != null: "UI manager should not be null";
        assert storage != null: "Storage object should not be null";
        Task temp = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.save(taskList);
        return uiManager.getDeleteMessage(temp, taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof DeleteCommand) {
            DeleteCommand temp = (DeleteCommand) obj;
            if (temp.index == this.index) {
                return true;
            }
        }
        return false;
    }
}

package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;
import duke.task.Task;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        assert taskList != null: "Task list should not be null";
        assert uiManager != null: "UI manager should not be null";
        assert storage != null: "Storage object should not be null";
        Task temp = taskList.getTask(index);
        temp.unmark();
        storage.save(taskList);
        return uiManager.getUnmarkMessage(temp);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof UnmarkCommand) {
            UnmarkCommand temp = (UnmarkCommand) obj;
            if (temp.index == this.index) {
                return true;
            }
        }
        return false;
    }
}

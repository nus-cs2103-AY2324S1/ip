package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;
import duke.task.Task;

import java.util.Objects;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        assert taskList != null: "Task list should not be null";
        assert uiManager != null: "UI manager should not be null";
        assert storage != null: "Storage object should not be null";
        Task temp = taskList.getTask(index);
        temp.mark();
        storage.save(taskList);
        return uiManager.getMarkMessage(temp);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof MarkCommand) {
            MarkCommand temp = (MarkCommand) obj;
            if (this.index == temp.index) {
                return true;
            }
        }
        return false;
    }

}

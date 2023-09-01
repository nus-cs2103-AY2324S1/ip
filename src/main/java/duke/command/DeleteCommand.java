package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task temp = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.printDeleteMessage(temp, taskList);
        storage.save(taskList);
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

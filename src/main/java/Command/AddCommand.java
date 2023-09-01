package Command;
import Task.*;
import Ui.*;
import Duke.*;
import TaskList.TaskList;
import Storage.Storage;

public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(taskToAdd);
        storage.saveTask(taskList.getTasks());
        ui.showTaskAdded(taskToAdd, taskList.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
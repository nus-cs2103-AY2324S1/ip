package Command;
import Task.*;
import Ui.*;
import Duke.*;
import TaskList.TaskList;
import Storage.Storage;

public class ToDoCommand extends Command {
    private Task taskToDo;

    public ToDoCommand(Task taskToDo) {
        this.taskToDo = taskToDo;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(taskToDo);
        storage.saveTask(taskList.getTasks());
//        ui.showTaskAdded(taskToDo, taskList.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

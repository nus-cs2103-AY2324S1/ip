package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

public class AddCommand implements Command {
    private final Task task;
    public AddCommand (Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(task);
        storage.save(taskList.getList());
        ui.showMessage("Task " + task.task + " successfully added");
        taskList.printSize();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

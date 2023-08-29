package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        ui.delete(task, taskList.size());
        storage.saveListToDisk(taskList.getList());
    }

}

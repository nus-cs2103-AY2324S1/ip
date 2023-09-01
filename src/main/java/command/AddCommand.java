package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import task.Task;

public class AddCommand extends Command {

    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        super(false);
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(this.taskToAdd, true);
        storage.writeListToFile(taskList);
        taskList.printTaskListInString();
    }

}

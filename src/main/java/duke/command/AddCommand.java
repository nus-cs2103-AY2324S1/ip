package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;
public class AddCommand extends Command{

    private String taskDescription;

    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(this.taskDescription, storage, ui);
    }

}

package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.showAllTasks(ui);
    }
}

package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList.getAllTasks());
    }
}

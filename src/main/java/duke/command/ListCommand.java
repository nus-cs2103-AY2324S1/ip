package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents the command to list out the tasks in the task list.
 */
public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList.getAllTasks());
    }
}

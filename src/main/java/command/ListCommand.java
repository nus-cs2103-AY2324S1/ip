package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTaskList();
    }
}

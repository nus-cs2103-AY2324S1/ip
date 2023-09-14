package command;

import main.Storage;
import main.TaskList;
import main.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getAllTasks(), "Here are the tasks in your list:");
    }
}

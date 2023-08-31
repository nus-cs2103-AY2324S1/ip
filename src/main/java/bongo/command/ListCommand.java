package bongo.command;
import bongo.task.TaskList;
import bongo.helper.Ui;
import bongo.helper.Storage;

public class ListCommand extends Command {

    /**
     * A constructor for a ListCommand.
     */
    public ListCommand() {};
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showAllTasks(tasks.getAllTasks());
    }
}

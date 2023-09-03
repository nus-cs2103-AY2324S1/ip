package duke.command;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.TaskList;


/**
 * ListsCommand lists down all the tasks in the TaskList class
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        ui.displayTasks(tasks);
    }
}

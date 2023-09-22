package duke.command;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.TaskList;


/**
 * ListsCommand lists down all the tasks in the TaskList class
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) {
        assert tasks != null : "tasks is initialised properly before showing its contents";
        return ui.displayTasks(tasks);
    }
}

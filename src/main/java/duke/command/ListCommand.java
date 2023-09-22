package duke.command;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ListCommand extends Command {
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return ui.showTasks(taskList);
    }
}

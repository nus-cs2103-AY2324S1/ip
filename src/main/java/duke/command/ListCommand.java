package duke.command;
import duke.Ui;
import duke.task.TaskList;
import duke.Storage;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}

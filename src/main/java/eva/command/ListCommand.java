package eva.command;
import eva.Ui;
import eva.task.TaskList;
import eva.Storage;

public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}

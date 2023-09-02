package bob.command;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Display exit lines
     * @param tasks from Bob class
     * @param ui from Bob class
     * @param storage from Bob class
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}

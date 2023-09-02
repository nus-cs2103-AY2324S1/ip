package bob.command;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class DisplayCommand extends Command {

    public DisplayCommand() {
        super.isExit = false;
    }

    /**
     * Displays list
     * @param tasks from Bob class
     * @param ui from Bob class
     * @param storage from Bob class
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.stringFormat(tasks.displayList());
    }
}

package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Command to exit the app.
 */
public class ExitCommand implements Command {
    /**
     * Prints exit statement.
     *
     * @param taskList TaskList.
     * @param ui UI.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("     BYE!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

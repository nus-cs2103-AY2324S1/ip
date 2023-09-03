package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/** Command to exit the app */
public class ExitCommand implements Command {
    /**
     * Prints exit statement.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui UI that interacts between the app and the user.
     * @param storage Storage to save or load data from file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showMessage("BYE!");
    }

    /**
     * Set isExit to true to exit the app.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

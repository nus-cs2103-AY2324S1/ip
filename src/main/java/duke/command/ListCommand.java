package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command{
    private boolean isExit = false;

    /**
     * Executes the ListCommand by displaying all tasks in the task list.
     *
     * @param tasks   The list of tasks to be displayed.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String res = "Here are the tasks in your list:";

        for (int i = 1; i <= tasks.size(); i++) {
            res += "\n" + i + ". " + tasks.get(i - 1);
        }

        ui.printMessage(res);
    }

    /**
     * Checks if the ListCommand should trigger the program to exit.
     *
     * @return False since listing tasks does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}

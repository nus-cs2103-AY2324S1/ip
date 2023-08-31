package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Print List command to be executed.
 */
public class PrintListCommand extends Command {
    public PrintListCommand() {
        super(false);
    }

    /**
     * Executes the list of commands to print the TaskList.
     *
     * @param list The given TaskList to be printed.
     * @param ui The given Ui to print the TaskList.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}

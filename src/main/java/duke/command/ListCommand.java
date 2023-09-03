package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ListCommmand class is for the "list" command.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getAllTasks(), "Here are the tasks in your list:");
    }

    /**
     * Checks whether it is an exit command.
     *
     * @return Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a unmark command to be executed.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the list of commands to unmark a Task from a TaskList.
     *
     * @param list The given TaskList with the Task to be unmarked.
     * @param ui The given Ui to show the status of the unmarking of the task.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task task = list.unmark(this.index);
        ui.printDelete(task, list.size());
    }
}

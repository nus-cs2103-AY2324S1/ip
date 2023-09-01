package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a Mark command to be executed.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the list of commands to mark a Task from a TaskList.
     *
     * @param taskList The given TaskList with the Task to be marked.
     * @param ui The given Ui to show the status of the marking of the task.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.mark(this.index);
        ui.printDelete(task, taskList.size());
    }
}

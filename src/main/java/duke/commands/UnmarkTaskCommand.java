package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.tasks.Task;

/**
 * Command used to unmark a Task in the TaskList
 */
public class UnmarkTaskCommand extends Command {
    private final int idx;
    /**
     * Constructs a MarkTaskCommand
     * @param idx Index of task to mark
     */
    public UnmarkTaskCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = taskList.unmarkTask(idx);
            ui.printToScreen("OK, I've marked this task as not done yet:\n"
                    + t);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showError(String.format("%d is not a valid index! Unable to unmark task.", idx + 1));
        }
    }
}

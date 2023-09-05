package duke.commands;

import java.io.IOException;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.tasks.Task;

/**
 * Command to mark a Task in the TaskList
 */
public class MarkTaskCommand extends Command {
    private final int idx;
    /**
     * Constructs a MarkTaskCommand
     * @param idx Index of task to mark
     */
    public MarkTaskCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = taskList.markTask(idx);
            ui.printToScreen("Nice! I've marked this task as done:\n"
                    + t);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showError(String.format("%d is not a valid index! Unable to mark task.", idx + 1));
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}

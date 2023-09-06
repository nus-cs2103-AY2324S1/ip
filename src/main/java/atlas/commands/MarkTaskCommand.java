package atlas.commands;

import java.io.IOException;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;
import atlas.tasks.Task;

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
            storage.save(taskList);
            ui.printToScreen("Nice! I've marked this task as done:\n"
                    + "\t" + t);
        } catch (IndexOutOfBoundsException e) {
            ui.showError(String.format("%d is not a valid index! Unable to mark task.", idx + 1));
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task t = taskList.markTask(idx);
            storage.save(taskList);
            return "Nice! I've marked this task as done:\n"
                    + "\t" + t;
        } catch (IndexOutOfBoundsException e) {
            return String.format("%d is not a valid index! Unable to mark task.", idx + 1);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}

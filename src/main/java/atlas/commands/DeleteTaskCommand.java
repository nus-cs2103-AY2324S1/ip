package atlas.commands;

import java.io.IOException;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;
import atlas.tasks.Task;


/**
 * Command to delete a Task from the TaskList
 */
public class DeleteTaskCommand extends Command {
    private final int idx;

    /**
     * Constructs a DeleteTaskCommand
     * @param idx Index of task to mark
     */
    public DeleteTaskCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = taskList.deleteTask(idx);
            storage.save(taskList);
            ui.printToScreen("Noted. I've removed this task:\n"
                    + "\t" + t);
            ui.printToScreen(taskList.getCountString());
        } catch (IndexOutOfBoundsException e) {
            ui.showError(String.format("%d is not a valid index! Unable to delete task.", idx + 1));
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Task t = taskList.deleteTask(idx);
            storage.save(taskList);
            return String.format("Noted. I've removed this task:\n"
                    + "\t%s\n"
                    + "%s", t, taskList.getCountString());
        } catch (IndexOutOfBoundsException e) {
            return String.format("%d is not a valid index! Unable to delete task.", idx + 1);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}

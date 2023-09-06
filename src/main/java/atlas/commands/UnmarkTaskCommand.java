package atlas.commands;

import java.io.IOException;
import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;
import atlas.tasks.Task;

/**
 * Command to unmark a Task in the TaskList
 */
public class UnmarkTaskCommand extends Command {
    private final int[] idx;

    /**
     * Constructs a MarkTaskCommand
     * @param idx Index of task to mark
     */
    public UnmarkTaskCommand(int... idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            List<Task> tasksNotDone = taskList.unmarkTasks(idx);
            storage.save(taskList);
            ui.printToScreen("OK, I've marked these tasks as not done yet:");
            for (Task t : tasksNotDone) {
                ui.printToScreen(String.format("\t%s\n", t));
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Not a valid index! Unable to unmark task.");
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            List<Task> tasksNotDone = taskList.unmarkTasks(idx);
            storage.save(taskList);
            StringBuilder output = new StringBuilder("OK, I've marked these tasks as not done yet:\n");
            for (Task t : tasksNotDone) {
                output.append(String.format("%s\n", t));
            }
            return output.toString();
        } catch (IndexOutOfBoundsException e) {
            return "Not a valid index! Unable to unmark task.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}

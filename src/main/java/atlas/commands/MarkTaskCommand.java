package atlas.commands;

import java.io.IOException;
import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;
import atlas.tasks.Task;

/**
 * Command to mark a Task in the TaskList
 */
public class MarkTaskCommand extends Command {
    private final int[] idx;
    /**
     * Constructs a MarkTaskCommand
     * @param idx Index of task to mark
     */
    public MarkTaskCommand(int... idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            List<Task> tasksDone = taskList.markTasks(idx);
            storage.save(taskList);
            ui.printToScreen("Nice! I've marked these tasks as done:");
            for (Task t : tasksDone) {
                ui.printToScreen("\t" + t);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Not a valid index! Unable to mark task.");
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            List<Task> tasksDone = taskList.markTasks(idx);
            storage.save(taskList);
            StringBuilder output = new StringBuilder("Nice! I've marked these tasks as done:\n");
            for (Task t : tasksDone) {
                output.append(String.format("%s\n", t));
            }
            return output.toString();
        } catch (IndexOutOfBoundsException e) {
            return "Not a valid index! Unable to mark task.";
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}

package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Constructs a DeleteCommand instance.
     *
     * @param taskNum The task number to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the DeleteCommand, removing a task from the list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The data storage.
     */
    @Override
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage) {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task deletedTask = tasks.remove(taskNum - 1);

            Ui.showHorizontalLine();
            System.out.println("    Noted. I've removed this task:\n" + "     " + deletedTask.toString());
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            Ui.showHorizontalLine();
            storage.saveTasks(tasks); // Save after deleting
        }
    }
}

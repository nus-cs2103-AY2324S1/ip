package commands;

import data.TaskList;
import data.exception.DukeException;
import data.tasks.Task;
import storage.Storage;
import ui.Ui;

/**
 * The DeleteCommand class.
 * Handles deleting a {@link Task} from {@link TaskList}.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * The constructor method for a DeleteCommand class.
     * Takes in the index of the task to be deleted from {@link TaskList}.
     * 
     * @param taskIndex The index of the task to be deleted.
     * @throws DukeException Thrown when the taskIndex given
     *                       cannot be converted to a number.
     */
    public DeleteCommand(String taskIndex) throws DukeException {
        try {
            this.taskIndex = Integer.parseInt(taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeException(new String[] {
                Ui.cTxt("delete", Ui.COLOR.PURPLE)
                + " takes in a number. Try delete 1."
            });
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task removedTask = tasks.delete(taskIndex - 1);
        ui.displayMsg(new String[] {
            "Okie! I've deleted task " + taskIndex + ":",
            "  " + removedTask.toString(),
            "Total no. of tasks stored: " + tasks.getSize()
        });

        // Write modified task list to file
        storage.update(tasks);
    }
}

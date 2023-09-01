package commands;

import data.TaskList;
import data.exception.DukeException;
import data.tasks.Task;
import storage.Storage;
import ui.Ui;

/**
 * The MarkCommand class.
 * Handles marking/unmarking a {@link Task}.
 */
public class MarkCommand extends Command {
    private int taskCount;
    private boolean isDone;

    /**
     * The constructor method of the MarkCommand class.
     * Takes in the index of the {@link Task} and a boolean
     * indicating whether it is to be marked/unmarked.
     * 
     * @param taskCount The index of the task.
     * @param isDone Indicates whether the task is to be marked/unmarked.
     * @throws DukeException Thrown when the taskCount given
     *                       cannot be converted to a number.
     */
    public MarkCommand(String taskCount, boolean isDone) throws DukeException {
        try {
            this.taskCount = Integer.parseInt(taskCount);
            this.isDone = isDone;
        } catch (NumberFormatException e) {
            throw new DukeException(new String[] {
                Ui.cTxt((isDone ? "mark" : "unmark"), Ui.Color.PURPLE)
                        + " takes in a number. Try "
                        + Ui.cTxt("mark", Ui.Color.PURPLE) + " 1."
            });
        }
    }

    @Override
    public void execute(
            TaskList tasks, Storage storage, Ui ui) throws DukeException {
        // User tries to mark/unmark a task that is out of bounds.
        if (taskCount < 1 || taskCount > tasks.getSize()) {
            throw new DukeException(String.format(
                "Unable to %s task %d :( You have %d task(s) stored.",
                isDone ? "mark" : "unmark", taskCount, tasks.getSize()
            ));
        }

        // Mark or unmark the task if the taskCount given is correct.
        Task task = tasks.mark(taskCount - 1, isDone);

        String success = isDone
            ? "Nice, I've marked this task as done:"
            : "Okie, I've marked this task as not done yet:";

        ui.displayMsg(new String[]{
            success,
            "  " + task.toString()
        });

        storage.update(tasks);
    }
}

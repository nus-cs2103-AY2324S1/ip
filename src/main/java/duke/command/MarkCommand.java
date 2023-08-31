package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index; //index of task to mark

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Executes the MarkCommand by marking a task as done, displaying a completion message,
     * and saving the updated task list to storage.
     *
     * @param taskList The list of tasks to operate on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     * @throws DukeException If the provided task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            Task markTask = tasks.get(index);
            tasks.markDone(index);
            ui.printMessage("Nice! I've marked this task as done:\n\t",markTask);
            storage.saveTasksToFile(tasks);
        } else {
            throw new DukeException("☹ OOPS!!! Please provide a valid task index to mark.");
        }
    }
}

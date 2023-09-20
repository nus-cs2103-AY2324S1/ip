package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task as done in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;
    private boolean isExit = false;

    /**
     * Constructs an UnmarkCommand with the index of the task to be unmarked.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand by marking the specified task as not done in the task list.
     *
     * @param tasks   The list of tasks to which the task's status will be updated.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for updating task data.
     * @throws DukeException If there is an issue executing the command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert this.index < 0 : "Index cannot be less than 0!";

        if (this.index + 1 > tasks.size()) {
            throw new DukeException("The current number of tasks is " + tasks.size()
                    + ", so you can't unmark task " + (index + 1) + "!!.");
        }

        //update
        Task task = tasks.get(index);
        tasks.unmarkTask(index);
        storage.updateFile(tasks);
        ui.updateLatestUnmarked(index);

        String type = findType(task);
        String res = "OK, I've marked this task as not done yet:" + "\n"
                + "[" + type + "]" + "[" + task.getStatusIcon() + "] "
                + task.getDescription();
        ui.updateMessage(res);
        ui.updateRecentCommand("unmark");
    }
    private String findType(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else if (task instanceof Event) {
            return "E";
        }

        return "";
    }

    /**
     * Checks if the UnmarkCommand should trigger the program to exit.
     *
     * @return False since unmarking a task does not trigger an exit.
     */
    public boolean isExit() {
        return this.isExit;
    }
}

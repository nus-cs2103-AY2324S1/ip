package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Command class to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private int taskNumber = -1;

    /**
     * Constructs an unmark command with tasknumber to unmark.
     *
     * @param taskNumber
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command. Mark task of taskNumber passed in as not done.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskNumber >= tasks.getSize()) {
            throw new DukeException("The task number you are trying to unmark "
                    + "does not exist yet.");
        }
        Task task = tasks.undoTask(taskNumber);
        ui.display("    I've marked this task as not done:");
        ui.display("    " + task);
        try {
            storage.save(tasks.saveToStorage());
        } catch (IOException e) {
            throw new DukeException(
                    "Something went wrong while trying to save the tasks to the disk!");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof UnmarkCommand)) {
            return false;
        }

        UnmarkCommand o = (UnmarkCommand) other;
        return this.taskNumber == o.taskNumber;
    }
}

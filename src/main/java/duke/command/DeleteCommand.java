package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Command class to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskNumber = -1;

    /**
     * Constructs a delete command to delete task taskNumber.
     *
     * @param taskNumber
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command. Delete the task according to the taskNumber initialised.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskNumber >= tasks.getSize() || this.taskNumber < 0) {
            throw new DukeException("The task number you are trying to delete "
                    + "does not exist yet.");
        }
        Task task = tasks.deleteTask(taskNumber);
        ui.display("    I've deleted this task:");
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

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand o = (DeleteCommand) other;
        return this.taskNumber == o.taskNumber;
    }
}

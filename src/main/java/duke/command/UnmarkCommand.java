package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Response;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The UnmarkCommand class represents a command to mark a task as not done.
 * It is a subclass of the Command class.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructs an UnmarkCommand object with the provided command details.
     *
     * @param commandDetails The list of command details, which includes the task number to mark as not done.
     */
    public UnmarkCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandDetails.get(0));

            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new DukeException("OOPS!!! The task number is out of range.");
            }

            Task unmarkedTask = tasks.get(taskNumber - 1);
            unmarkedTask.markAsNotDone();
            storage.writeListToFile(tasks);
            return response.printTaskUnmarked(unmarkedTask);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The task number cannot be parsed.");
        }
    }

    /**
     * Checks if this UnmarkCommand is equal to another object. Two UnmarkCommands are considered equal
     * if they have the same command details.
     *
     * @param obj The object to compare to this UnmarkCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UnmarkCommand) {
            UnmarkCommand other = (UnmarkCommand) obj;
            if (this.commandDetails == null || other.commandDetails == null) {
                return false;
            }
            return this.commandDetails.equals(other.commandDetails);
        }
        return false;
    }
}

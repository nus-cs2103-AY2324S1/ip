package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Response;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The DeleteCommand class represents a command to delete a task.
 * It is a subclass of the Command class.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand object with the provided command details.
     *
     * @param commandDetails The list of command details, which includes the task number to delete.
     */
    public DeleteCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) throws DukeException {
        int taskNumber = 0;
        try {
            taskNumber = Integer.parseInt(commandDetails.get(0));
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The task number cannot be parsed.");
        }
        if (taskNumber > tasks.size() || taskNumber <= 0) {
            throw new DukeException("OOPS!!! The task number is out of range.");
        }
        Task deletedTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        storage.writeListToFile(tasks);
        return response.printTaskDeleted(deletedTask, tasks.size());
    }

    /**
     * Checks if this DeleteCommand is equal to another object. Two DeleteCommands are considered equal
     * if they have the same command details.
     *
     * @param obj The object to compare to this DeleteCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DeleteCommand) {
            DeleteCommand other = (DeleteCommand) obj;
            if (this.commandDetails == null || other.commandDetails == null) {
                return false;
            }
            return this.commandDetails.equals(other.commandDetails);
        }
        return false;
    }
}

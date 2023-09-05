package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class DeleteComand extends Command {

    /**
     * Constructs a DeleteCommand object with the provided command details.
     *
     * @param commandDetails The list of command details, which includes the task number to delete.
     */
    public DeleteComand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        int taskNumber = 0;
        try {
            taskNumber = Integer.parseInt(commandDetails.get(0));
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The task number cannot be parsed.");
        }
        if (taskNumber > tasks.size()) {
            throw new DukeException("☹ OOPS!!! The task number is out of range.");
        }
        Task deletedTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        storage.writeListToFile(tasks);
        ui.printTaskDeleted(deletedTask, tasks.size());
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
        if (obj instanceof DeleteComand) {
            DeleteComand other = (DeleteComand) obj;
            if (this.commandDetails == null || other.commandDetails == null) {
                return false;
            }
            return this.commandDetails.equals(other.commandDetails);
        }
        return false;
    }
}

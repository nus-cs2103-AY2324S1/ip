package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The MarkCommand class represents a command to mark a task as done.
 * It is a subclass of the Command class.
 */
public class MarkCommand extends Command {

    /**
     * Constructs a MarkCommand object with the provided command details.
     *
     * @param commandDetails The list of command details, which includes the task number to mark as done.
     */
    public MarkCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNumber = 0;
        try {
            taskNumber = Integer.parseInt(commandDetails.get(0));
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! The task number cannot be parsed.");
        }
        if (taskNumber > tasks.size()) {
            throw new DukeException("☹ OOPS!!! The task number is out of range.");
        }
        Task markedTask = tasks.get(taskNumber - 1);
        markedTask.markAsDone();
        storage.writeListToFile(tasks);
        ui.printTaskMarked(markedTask);
    }

    /**
     * Checks if this MarkCommand is equal to another object. Two MarkCommands are considered equal
     * if they have the same command details.
     *
     * @param obj The object to compare to this MarkCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MarkCommand) {
            MarkCommand other = (MarkCommand) obj;
            if (this.commandDetails == null || other.commandDetails == null) {
                return false;
            }
            return this.commandDetails.equals(other.commandDetails);
        }
        return false;
    }
}

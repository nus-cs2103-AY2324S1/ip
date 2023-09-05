package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;


/**
 * The ListCommand class represents a command to list all tasks.
 * It is a subclass of the Command class.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object with the provided command details.
     *
     * @param commandDetails The list of command details (not used in this command).
     */
    public ListCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }

    /**
     * Checks if this ListCommand is equal to another object. Two ListCommands are considered equal
     * if they have the same command details (not used in this command).
     *
     * @param obj The object to compare to this ListCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ListCommand) {
            ListCommand other = (ListCommand) obj;
            if (this.commandDetails == null || other.commandDetails == null) {
                return false;
            }
            return this.commandDetails.equals(other.commandDetails);
        }
        return false;
    }
}

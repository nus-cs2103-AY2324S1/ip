package duke.command;

import java.util.ArrayList;

import duke.Response;
import duke.Storage;
import duke.task.TaskList;

/**
 * The ByeCommand class represents a command to exit.
 * It is a subclass of the Command class.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand object with the provided command details.
     *
     * @param commandDetails The list of command details (not used in this command).
     */
    public ByeCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public String execute(TaskList tasks, Response response, Storage storage) {
        return "Bye.";
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return True, indicating that this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Checks if this ByeCommand is equal to another object. Two ByeCommands are considered equal
     * if they have the same command details (not used in this command).
     *
     * @param obj The object to compare to this ByeCommand.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByeCommand) {
            ByeCommand other = (ByeCommand) obj;
            if (this.commandDetails == null || other.commandDetails == null) {
                return false;
            }
            return this.commandDetails.equals(other.commandDetails);
        }
        return false;
    }
}

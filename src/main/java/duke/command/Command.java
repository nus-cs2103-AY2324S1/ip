package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Response;
import duke.Storage;
import duke.task.TaskList;


/**
 * The Command abstract class represents a generic command.
 */
public abstract class Command {
    protected ArrayList<String> commandDetails;

    /**
     * Constructs a Command object with the provided command details.
     *
     * @param commandDetails The list of command details, if any, required for command execution.
     */
    public Command(ArrayList<String> commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks The TaskList to manipulate, if needed, during command execution.
     * @param response The Response object to generate response.
     * @param storage The Storage object to read from or write to data storage.
     * @throws DukeException If there is an issue during command execution.
     */
    public abstract String execute(TaskList tasks, Response response, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

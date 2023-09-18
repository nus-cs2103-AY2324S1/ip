package command;

import duke.DukeException;
import duke.Storage;
import task.TaskList;

/**
 * Represents a command that a user may pass into the Duke chatbot
 */
public class Command {
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return "This method is to be implemented by child classes";
    }
}

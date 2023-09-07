package command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import task.TaskList;

/**
 * Represents a command that a user may pass into the Duke chatbot
 */
public class Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "This method is to be implemented by child classes";
    }
}

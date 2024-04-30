package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents commands inputted into the chatbot.
 */
public class Command {
    /**
     * Converts the task to its appropriate form and completes the command.
     * 
     * @param taskList The task list to be stored or deleted in.
     * @param ui The Ui that deals with the interaction with the user.
     * @param storage The storage object that helps in loading and storing of tasks.
     * @throws DukeException if the inputs are invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {};

    /**
     * Represents whether the command is an exit command or not.
     * 
     * @return True if it is an exit command, false if otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

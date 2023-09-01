package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    /**
     * Returns true if this is exit command.
     * @return True if this is exit command.
     */
    public boolean isExit() {
        return false;
    }


    /**
     * Executes this command.
     * @param taskList Task list that this command need to access.
     * @param ui The way command interact with user.
     * @param storage Storage can store and load file where command need to execute
     * @throws DukeException When there is an exception.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}

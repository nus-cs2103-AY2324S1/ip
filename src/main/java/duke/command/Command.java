package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The Command class.
 */
public abstract class Command {
    /**
     * Creates a new task, adds it to the task list, stores it in the file,
     * and prints out a message.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates if the program is supposed to end or not after this command is executed.
     */
    public abstract boolean isExit();
}

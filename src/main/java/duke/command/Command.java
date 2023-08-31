package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates if the program is supposed to end or not after this command is executed.
     */
    public abstract boolean isExit();
}

package qi.command;

import qi.qiexception.QiException;
import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

/**
 * Represents the general command from users.
 * More specific command classes extend this class.
 */
public abstract class Command {
    /**
     * Updates the data and shows messages to the users according
     * to the command.
     *
     * @param tasks TaskList recording the current task in the list.
     * @param ui Ui representing the current User Interface (UI).
     * @param storage Storage updating data if necessary.
     * @return String notification created by Ui.
     * @throws QiException If there are problems with accessing the data in the hard disk.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws QiException;
}

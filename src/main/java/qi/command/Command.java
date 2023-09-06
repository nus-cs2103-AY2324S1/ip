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
    private boolean isExit;

    /**
     * Takes in a status to decide whether this is
     * the last interaction with users or not.
     *
     * @param isExit Boolean value indicating whether to exit
     *               after the interaction or not.
     */
    protected Command(Boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Updates the data and shows messages to the users according
     * to the command.
     *
     * @param tasks TaskList recording the current task in the list.
     * @param ui Ui representing the current User Interface (UI).
     * @param storage Storage updating data if necessary.
     * @throws QiException If there are problems with accessing the data in the hard disk.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws QiException;

    public boolean isExit() {
        return this.isExit;
    }
}

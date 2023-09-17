package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import ui.Ui;

/**
 * An Abstract class that represents the action that will be done by the different command input
 * by the user.
 */
public abstract class Command {

    protected boolean isExit = false;

    /**
     * Returns whether the program should stop running as the end point "bye" is hit.
     *
     * @return A boolean value shows whether the program should be completed and exited.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     *  Executes an abstract method that will execute the task provided depending on the user input.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public abstract String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException;
}

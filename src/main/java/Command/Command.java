package Command;

import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;

/**
 * An Abstract class that represents the action that will be done by the different command input
 * by the user.
 */
public abstract class Command {

    protected boolean isExit = false;

    /**
     * Returns whether the program should stop running as the end point "bye" is hit.
     *
     * @return boolean value shows whether the program should be completed and exited.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * An abstract method that will excute the task provided depending on the user input.
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     * @throws DukeException If user inputs is invalid.
     */
    public abstract void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException;
}

package duke.commands;

import java.io.IOException;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {
    /**
     * Executes command based on the command type.
     * Subtype will inherit and implement the method.
     *
     * @param taskList All Tasks created.
     * @param ui UI to display message.
     * @param storage Object to write and store data.
     * @throws IOException  If file not found or corrupted.
     * @throws DukeException If invalid user input is passed to command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    /** Breaks the loop and exit the program. */
    public boolean isExit() {
        return false;
    }
}

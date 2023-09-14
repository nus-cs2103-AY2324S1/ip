package duke.commands;

import java.io.IOException;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * The Command class is an abstract class with an
 * abstract method execute for subclasses to implement
 */
public abstract class Command {
    /**
     * Executes command based on the command type.
     * Subtype will inherit and implement the method.
     *
     * @param taskList All Tasks created.
     * @param storage Object to write and store data.
     * @throws IOException  If file not found or corrupted.
     * @throws DukeException If invalid user input is passed to command.
     */
    public abstract String execute(TaskList taskList, Message message, Storage storage)
            throws DukeException, IOException;
}

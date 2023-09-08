package duke.command;

import java.util.Objects;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Represents a user command.
 */
public interface Command {

    /**
     * Execute the given command.
     *
     * @param taskList the current TaskList
     * @param ui       the UI tied to the program
     * @param storage  the Storage tied to the program
     * @throws DukeException if there is error running the command
     */
    default void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        Objects.requireNonNull(taskList, "TaskList cannot be null");
        Objects.requireNonNull(storage, "storage cannot be null");
    }

}

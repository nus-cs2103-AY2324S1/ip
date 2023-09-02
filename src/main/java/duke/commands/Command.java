package duke.commands;

import duke.TaskListStorage;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.MissingDescriptionException;
/**
 * Represents a command that can be executed by Duke.
 */
public interface Command {
    /**
     * Executes the command.
     * 
     * @param taskListStorage The TaskListStorage object that contains the task list.
     * @throws InvalidIndexException If the index provided is invalid.
     * @throws MissingDescriptionException If the description is missing.
     * @throws IncorrectCommandFormatException If the command is in the wrong format.
     */
    void execute(TaskListStorage taskListStorage)
            throws InvalidIndexException, MissingDescriptionException, IncorrectCommandFormatException;
}
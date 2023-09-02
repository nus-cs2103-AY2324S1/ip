package duke.commands;

import duke.TaskListStorage;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.MissingDescriptionException;

public interface Command {
    void execute(TaskListStorage taskListStorage)
            throws InvalidIndexException, MissingDescriptionException, IncorrectCommandFormatException;
}
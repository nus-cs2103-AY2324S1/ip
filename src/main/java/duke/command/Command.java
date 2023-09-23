package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command that can be executed within the chatbot.
 */
@FunctionalInterface
public interface Command {

    /**
     * Executes the command and determining whether the program should continue running.
     *
     * @param tasks The task list that may be modified by the command.
     * @param ui    The user interface for analyzing chat history.
     * @return true if terminate, false otherwise.
     * @throws DukeException for error.
     */
    boolean execute(TaskList tasks, Ui ui) throws DukeException;

}

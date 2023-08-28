package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that can be executed within the chatbot.
 */
@FunctionalInterface
public interface  Command {

    /**
     * Executes the command and determining whether the program should continue running.
     *
     * @param tasks The task list that may be modified by the command.
     * @param ui The user interface for analyzing chat history.
     * @return {@code true} if the program should terminate after executing this command, or
     *         {@code false} if the program should continue running.
     */
    boolean execute(TaskList tasks, Ui ui) throws DukeException;

}


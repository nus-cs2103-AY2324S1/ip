package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a generic command interface with a run method defining the
 * behaviour of the command.
 */
public interface Command {
    /**
     * Executes the command given an input.
     *
     * @param input The command to be run.
     * @param taskList The application's task list.
     * @param ui       The UI of the application.
     */
    void run(String input, TaskList taskList, Ui ui);
}

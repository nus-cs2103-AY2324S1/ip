package duke.commands;

import duke.TaskList;

/**
 * Represents a generic command interface with a run method defining the
 * behaviour of the command.
 */
public interface Command {
    /**
     * Executes the command given an input.
     *
     * @param input    The command to be run.
     * @param taskList The application's task list.
     * @return The string output of the command's execution.
     */
    String run(String input, TaskList taskList);
}

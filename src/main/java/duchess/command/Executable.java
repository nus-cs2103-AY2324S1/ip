package duchess.command;

import duchess.TaskList;

/**
 * Interface of {@link Command}, or anything that can be executed on a TaskList for Duchess.
 * This can also be used to create lambda commands for Duchess.
 */
@FunctionalInterface
interface Executable {
    /**
     * The function to be executed when the Executable is run.
     * The Executable will operate on the TaskList, and return the response as a String.
     */
    String execute(String userInput, TaskList taskList);
}

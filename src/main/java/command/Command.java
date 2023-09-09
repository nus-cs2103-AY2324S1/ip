package command;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

/**
 * Represents an abstract command that can be executed.
 * This class is used to represent the command to be executed
 * based on type of input the user gives.
 */
public abstract class Command {

    /**
     * Executes the specific command implemented in subclasses.
     * The command execution can interact with the specified taskList, user interface, and storage.
     *
     * @param tasks The list of tasks on which the command will operate on.
     * @param ui The UI which is used during the command execution.
     * @param storage The storage where tasks are stored and retrieved from.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if the command ends the program.
     *
     * @return true if the command ends the program, false otherwise.
     */
    public abstract boolean isExit();
}

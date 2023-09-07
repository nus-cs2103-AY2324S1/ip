package prts.command;

import prts.*;
import prts.task.AlreadyMarkedException;
import prts.task.AlreadyUnmarkedException;

/**
 * Represents a general command that the user has input to PRTS.
 */
public abstract class Command {

    /**
     * Executes the command given by the user.
     * @param tasks The list of tasks currently stored.
     * @param ui The UI object stored by PRTS.
     * @param storage The Storage object stored by PRTS.
     * @throws SaveToFileException If the program fails to save the state of the TaskList to the save
     * file after the execution of TaskList-modifying commands.
     * @throws OutOfRangeException If the execution encounters user input that falls outside valid ranges.
     * @throws AlreadyMarkedException If the Task being marked is already marked.
     * @throws AlreadyUnmarkedException If the Task being unmarked is already unmarked.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SaveToFileException,
            OutOfRangeException, AlreadyMarkedException, AlreadyUnmarkedException;

    /**
     * Returns true if the user command is to exit the program, and false otherwise.
     * @return true if this command is an ExitCommand, and false otherwise.
     */
    public boolean isExit() {
        return false;
    };

}

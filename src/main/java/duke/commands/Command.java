package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the superclass of all the commands to be implemented
 */
public abstract class Command {
    boolean isExit;

    /**
     * Represents a constructor of the Commands abstract class
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Checks if the command is to exit the program
     * 
     * @return a boolean, true if it is to exit the program and false if it is not
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes command based on the command given
     * 
     * @param tasks
     * @param ui
     * @param storage
     * @return a String that is the output of the command
     * @throws Throwable
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Throwable;
}
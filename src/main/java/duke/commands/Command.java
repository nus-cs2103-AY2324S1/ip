package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/**
 * List of all possible commands, used the idea of enums to list
 * all the possible commands
 */
public abstract class Command {
    boolean isExit;

    /**
     * Constructor of the Commands abstract class
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
     * Execute command based on the command given
     * 
     * @param tasks
     * @param ui
     * @param storage
     * @return a String that is the output of the command
     * @throws Throwable
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Throwable;
}
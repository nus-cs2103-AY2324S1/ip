package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/*
 * An abstract class that represents all commands that user can possibly make
 * All commands user can make are subtypes of this class
 */
public abstract class Command {
    boolean isExit = false;

    /*
     * An abstract method that executes the command that user gave
     * 
     * @params tasks TaskList containing all existing Task objects
     * 
     * @params ui UI interface that is used to print messages to the terminak
     * 
     * @params storage Storage object that houses database of the program
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.isExit;
    }

}

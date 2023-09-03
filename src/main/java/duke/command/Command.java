package duke.command;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.TaskList;


/**
 * Abstract class Command to provide inheritance and allow for OOP for other Commands
 * @version 0.1
 * @since 2023-08-29
 */
public abstract class Command {

    /**
     * boolean to check if the command is an ExitCommand
     */
    private boolean isExit;
    /**
     * Normal constructor for command to initialise the Command class
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Executes the Command specified in each subclass of Command
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference to the Storage class
     * @throws DukeException throws a DukeException which indicates no description
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;


    /**
     * @return returns if the command is an ExitCommand
     */

    public boolean isExit() {
        return false;
    }

}

package duke.command;
import duke.task.TaskList;
import duke.task.DukeException;
import duke.helper.Ui;
import duke.helper.Storage;
/**
 * Abstract class Command to provide inheritance and allow for OOP
 * @version 0.1
 * @since 2023-08-29
 */
public abstract class Command {

    /**
     * Checks if the command is an ExitCommand so that it can exit the loop
     */
    boolean isExit;

    /**
     * Executes the Command specified in each subclass of Command
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference
     * @throws DukeException throws a DukeException which indicates no desc if no text left
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;

    /**
     * Normal constructor for command to initialise the command class
     */
    public Command() {
        this.isExit = false;
    }

    /**
     *
     * @return returns if the command is an ExitCommand
     */

    public boolean isExit() {
        return false;
    }

}

package command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

import task.TaskList;

/**
 * Represents a command that a user may pass into the Duke chatbot
 */
public class Command {
    protected boolean isExit = false;

    /**
     * Returns whether the command specified is an exit command
     *
     * @return true if the command is an exit command, false otherwise
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets the value of isExit
     *
     * @param newExit the new value of isExit
     */
    protected void setExit(boolean newExit) {
        this.isExit = newExit;
    }

    /**
     * Executes the command using the specified TaskList, Ui and Storage
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param ui Text Ui that the user interacts with
     * @param storage File path where the tasks are stored
     * @throws DukeException when invalid commands/command parameters are entered
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("This method is to be implemented by child classes");
    }
}

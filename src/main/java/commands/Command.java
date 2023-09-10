package commands;

import exceptions.DukeException;
import io.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    /** Command by a user in a line. */
    private String fullCommand;
    /** Indicate if command is terminal. */
    private boolean isExit;

    /**
     * Constructor for Command.
     * @param fullCommand
     */
    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
        this.isExit = false;
    }

    /**
     * Constructor for Command.
     */
    public Command() {
        this.fullCommand = "";
        this.isExit = false;
    }


    /**
     * Returns true if command is terminal.
     * @return isExit
     */
    public boolean canExit() {
        return this.isExit;
    }

    /**
     * Sets command to be terminal.
     */
    public void setExit() {
        this.isExit = true;
    }

    /**
     * Returns the command.
     * @return fullCommand
     */
    public String getFullCommand() {
        return this.fullCommand;
    }

    /**
     * Sets the command.
     * @param fullCommand
     */
    public void setCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command.
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

}

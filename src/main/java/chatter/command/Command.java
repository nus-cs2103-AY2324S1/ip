package chatter.command;

import chatter.ChatterException;
import chatter.Storage;
import chatter.TaskList;
import chatter.Ui;

/**
 * Represents an abstract chatter.command.Command class.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public abstract class Command {
    /** Boolean determining whether user wants to exist the chatbot. */
    private boolean isExit;

    /**
     * Constructor for chatter.command.Command class.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns the isExit boolean to let the chatbot know whether
     * to exit.
     *
     * @return isExit boolean.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Set the isExit boolean to true so that the chatbot will exit.
     */
    public void setExit() {
        this.isExit = true;
    }

    /**
     * Abstract method that executes the command
     *
     * @param tasks chatter.TaskList class storing an ArrayList of chatter.task.Task objects.
     * @param ui chatter.Ui class to handle user interactions.
     * @param storage chatter.Storage class to read and store tasks by the user.
     * @return String to show user that the command has been executed.
     * @throws ChatterException Any error that occurs due to the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ChatterException;
}

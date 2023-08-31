package pogo.commands;

import java.util.List;

import pogo.tasks.Task;

/**
 * The Command class is the base class for all commands.
 * Based on AddressBook-Level2
 */
public class Command {

    /**
     * The {@code Pogo} instance where the command is executed.
     */
    protected List<Task> tasks;

    protected Command() {
    }

    /**
     * Supplies the data to be used by the command.
     *
     * @param tasks The list of tasks.
     */
    public void setData(List<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Executes the command and returns the result.
     *
     * @return The result of the command.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes.");
    }
}

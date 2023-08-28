package pogo.commands;

import pogo.Pogo;
import pogo.tasks.Task;

import java.util.List;

/**
 * The Command class is the base class for all commands.
 */
public class Command {

    /**
     * The {@code Pogo} instance where the command is executed.
     */
    protected Pogo pogo;
    protected List<Task> tasks;

    protected Command() {
    }

    /**
     * Supplies the data to be used by the command.
     *
     * @param pogo
     */
    public void setData(Pogo pogo, List<Task> tasks) {
        this.pogo = pogo;
        this.tasks = tasks;
    }


    /**
     * Executes the command and returns the result.
     *
     * @return The result of the command.
     */
    public CommandResult execute() {
        return new CommandResult();
    }
}

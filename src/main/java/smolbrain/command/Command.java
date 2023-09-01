package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidRangeException;
import smolbrain.task.TaskList;

/**
 * Used for other classes to implement different commands for chatbot.
 */
public interface Command {

    /**
     * Executes this command.
     *
     * @param tasks List of tasks of chatbot.
     * @param ui Ui manager of chatbot.
     * @param storage Storage manager of chatbot.
     * @throws InvalidRangeException If the id given by user for array access is out of bound.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidRangeException;

    /**
     * Indicates if this command causes chatbot to exit.
     *
     * @return Boolean value if this command exits the chatbot.
     */
    public boolean isExit();

    /**
     * Sets the flag that chatbot is loading to true.
     */
    public void setLoading();

}

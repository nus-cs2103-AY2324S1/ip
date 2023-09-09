package ducky.command;

import ducky.DuckyException;
import ducky.Storage;
import ducky.TaskList;
import ducky.UserInterface;

/**
 * Represents a command to Ducky chatbot.
 */
public abstract class Command {

    /**
     * Returns whether command is an exit command.
     * @return True if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the current command.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param ui       UserInterface of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return
     * @throws DuckyException If exceptions specific to Ducky are raised.
     */
    public abstract String execute(TaskList taskList, UserInterface ui, Storage storage) throws DuckyException;
}

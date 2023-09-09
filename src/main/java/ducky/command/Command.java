package ducky.command;

import ducky.DuckyException;
import ducky.Storage;
import ducky.TaskList;

/**
 * Represents a command to Ducky chatbot.
 */
public abstract class Command {

    /**
     * Executes the current command.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return String indicating result of chatbot command.
     * @throws DuckyException If exceptions specific to Ducky are raised.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DuckyException;
}

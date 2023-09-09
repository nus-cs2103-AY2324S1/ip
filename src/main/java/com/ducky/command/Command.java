package com.ducky.command;

import com.ducky.logic.DuckyException;
import com.ducky.logic.Storage;
import com.ducky.logic.TaskList;

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

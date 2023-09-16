package minion.commands;

import java.io.IOException;

import minion.data.TaskList;
import minion.data.exception.MinionException;
import minion.storage.Storage;

/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Returns the command result given the tasks, ui, storage.
     * @param tasks Task list.
     * @param storage Storage of chatbot.
     * @throws MinionException if task cannot be executed.
     * @throws IOException if there is IO error.
     */
    public abstract CommandResult execute(TaskList tasks, Storage storage) throws MinionException, IOException;
}

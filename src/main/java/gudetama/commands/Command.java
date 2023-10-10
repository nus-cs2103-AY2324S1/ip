package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;

/**
 * Represents a command that can be executed by the chatbot
 */
public abstract class Command {
    /**
     * Abstract method that executes the command that adds the task
     * @param tasksList Task list which contains the tasks
     * @param ui A UI instance that displays a message to indicate to the user the task has been added
     * @param storage Storage instance that represents the storage of the file
     */
    public abstract String execute(TaskList tasksList, Ui ui, Storage storage);
}

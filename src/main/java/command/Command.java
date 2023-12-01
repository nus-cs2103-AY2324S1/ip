package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Command is an abstract class that performs the command the user types into
 * the chatbot.
 */
public abstract class Command {

    /**
     * Runs the command the user inputted in the chatbot.
     *
     * @param taskList The task list which the command adds the task into if asked.
     * @param ui The ui of the chatbot to read the input of the user.
     * @return The response of paimonbot when a specific command is given.
     * @throws Exception
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Check whether the command issues the chatbot to stop running.
     * If the command is 'bye', returns false.
     *
     * @return The boolean to determine the chatbot should stop running.
     */
    public abstract boolean isExit();
}

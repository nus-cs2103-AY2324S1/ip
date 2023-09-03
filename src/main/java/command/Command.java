package command;

import duke.Ui;
import duke.TaskList;

/**
 * Command is an abstract class that performs the command the user types into
 * the chatbot.
 */
public abstract class Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of Command.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to read the input of the user.
     */
    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Runs the command the user inputted in the chatbot.
     *
     * @param taskList The task list which the command adds the task into if asked.
     * @param ui The ui of the chatbot to read the input of the user.
     * @throws Exception
     */
    public abstract void execute(TaskList taskList, Ui ui) throws Exception;

    /**
     * Check whether the command issues the chatbot to stop running.
     * If the command is 'bye', returns false.
     *
     * @return The boolean to determine the chatbot should stop running.
     */
    public abstract boolean isExit();
}

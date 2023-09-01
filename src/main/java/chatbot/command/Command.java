package chatbot.command;

import chatbot.task.TaskManager;
import chatbot.Ui;
import chatbot.ChatbotException;

/**
 * Abstract base class for different types of commands that can be executed by the chatbot.
 */
public abstract class Command {

    /**
     * Executes the command by interacting with the TaskManager and UI.
     *
     * @param taskManager taskManager
     * @param ui Ui
     * @throws ChatbotException if there is error in the Command
     */
    public abstract void execute(TaskManager taskManager, Ui ui) throws ChatbotException;
    public abstract boolean isExit();
}

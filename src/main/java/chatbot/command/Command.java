package chatbot.command;

import chatbot.ChatbotException;
import chatbot.Ui;
import chatbot.task.TaskManager;


public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui) throws ChatbotException;

    public abstract boolean isExit();
}

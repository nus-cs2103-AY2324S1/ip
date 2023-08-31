package chatbot.command;

import chatbot.task.TaskManager;
import chatbot.Ui;
import chatbot.ChatbotException;

public abstract class Command {
    public abstract void execute(TaskManager taskManager, Ui ui) throws ChatbotException;
    public abstract boolean isExit();
}

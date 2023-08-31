package chatbot.command;

import chatbot.Ui;
import chatbot.ChatbotException;


public class AddDeadlineCommand extends chatbot.command.Command {
    private String description;
    private String time;

    public AddDeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(chatbot.task.TaskManager taskManager, Ui ui) throws ChatbotException {
        taskManager.addDeadlines(description, time);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

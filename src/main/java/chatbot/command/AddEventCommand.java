package chatbot.command;

import chatbot.Ui;
import chatbot.ChatbotException;
import chatbot.task.TaskManager;

public class AddEventCommand extends Command{
    private String description;
    private String start;
    private String end;

    public AddEventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws ChatbotException {
        taskManager.addEvents(description, start, end);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

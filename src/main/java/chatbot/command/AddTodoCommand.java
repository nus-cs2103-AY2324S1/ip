package chatbot.command;

import chatbot.Ui;
import chatbot.ChatbotException;
import chatbot.task.TaskManager;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws ChatbotException {
        taskManager.addTodo(description);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

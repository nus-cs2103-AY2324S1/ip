package chatbot.command;

import chatbot.ChatbotException;
import chatbot.Ui;
import chatbot.task.TaskManager;

/**
 * class which handle add todos command extends abstract class command.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * constructor for this class
     *
     * @param description description for todos task
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskManager taskManager) throws ChatbotException {
        try {
            return taskManager.addTodo(description);
        } catch (ChatbotException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

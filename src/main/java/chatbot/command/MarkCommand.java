package chatbot.command;

import chatbot.Ui;
import chatbot.ChatbotException;
import chatbot.task.TaskManager;

/**
 * class which handle mark task command extends abstract class command.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructor for this class.
     *
     * @param index index of the task
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskManager taskManager) throws ChatbotException {
           return taskManager.taskDone(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

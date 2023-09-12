package chatbot.command;

import chatbot.ChatbotException;
import chatbot.task.TaskManager;

/**
 * class which handle unmark command extends abstract class command.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * COnstructor for this class.
     *
     * @param index index of the task
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskManager taskManager) throws ChatbotException {
       return taskManager.unMarktask(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package chatbot.command;

import chatbot.Ui;
import chatbot.ChatbotException;
import chatbot.task.TaskManager;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws ChatbotException {
        taskManager.taskDone(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

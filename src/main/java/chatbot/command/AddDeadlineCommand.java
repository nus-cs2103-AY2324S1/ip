package chatbot.command;

import chatbot.Ui;
import chatbot.ChatbotException;


/**
 * class which handle addd Deaddline command extends abstract class command.
 */
public class AddDeadlineCommand extends chatbot.command.Command {
    private String description;
    private String time;

    /**
     * constructor for this class.
     *
     *  @param description description of the deadline
     * @param time time of the deadline
     */
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

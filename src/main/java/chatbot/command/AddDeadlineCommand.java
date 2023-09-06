package chatbot.command;

import chatbot.ChatbotException;
import chatbot.Ui;



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
    public String execute(chatbot.task.TaskManager taskManager) throws ChatbotException {
        try {
            String response = taskManager.addDeadlines(description, time);
            return response;
        } catch (ChatbotException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

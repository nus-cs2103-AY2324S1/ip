package chatbot.command;

import chatbot.ChatbotException;
import chatbot.Ui;
import chatbot.task.TaskManager;


/**
 * class which handle addd event command extends abstract class command.
 */
public class AddEventCommand extends Command{

    private String description;
    private String start;
    private String end;

    /**
     * Constructor for this class.
     *
     * @param description description of the Event
     * @param start starting time of the event
     * @param end endiong time for the event
     */
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

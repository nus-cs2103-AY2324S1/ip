package remy.command;

import remy.ChatbotException;
import remy.Parser;
import remy.Storage;
import remy.Ui;
import remy.task.Event;
import remy.task.TaskList;

/**
 * A Command that creates and adds an Event to the TaskList upon executing.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String eventName;
    private String startDate;
    private String endDate;

    /**
     * Creates new Event command that parses user input and check that the format is correct.
     *
     * @param input The String submitted by the user to the Chatbot.
     * @throws ChatbotException if input is missing information, or in the wrong format.
     */
    public EventCommand(String input) throws ChatbotException {
        // Check input has minimum number of characters required
        if (input.length() < 39) {
            throw new ChatbotException("missing info lah.");
        }

        // split input into eventName, startDate, endDate
        String[] parts = input.substring(6).split(" /from | /to ");

        // Check input has all required information
        if (parts.length != 3) {
            throw new ChatbotException("missing info lah. Use event EVENTNAME /from yyyy-mm-dd /to yyyy-mm-dd.");
        }

        // Store event name
        this.eventName = parts[0];

        // Check validity of start date and store
        if (Parser.checkValidDate(parts[1])) {
            this.startDate = parts[1];
        }

        // Check validity of end date and store
        if (Parser.checkValidDate(parts[2])) {
            this.endDate = parts[2];
        }
    }


    /**
     * Creates new Event and adds it to the TaskList.
     *
     * @param taskList The TaskList to be acted on.
     * @param ui       Handles User interaction.
     * @param storage  Handles saving the updated TaskList.
     * @throws ChatbotException if error while saving.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        Event temp = new Event(this.eventName, this.startDate, this.endDate);
        taskList.add(temp);
        storage.save(taskList);
        return Ui.getAddedTaskMessage(temp, taskList.size());
    }
}

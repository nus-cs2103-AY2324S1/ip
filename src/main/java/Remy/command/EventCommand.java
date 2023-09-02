package Remy.command;

import Remy.Task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;
import Remy.Task.Event;

/**
 * A Command that creates and adds an Event to the TaskList upon executing.
 */
public class EventCommand extends Command {
    private String eventName;
    private String startDate;
    private String endDate;
    public static final String COMMAND_WORD = "event";

    /**
     * Creates new Event command that parses user input and check that the format is correct.
     * @param input The String submitted by the user to the Chatbot.
     * @throws ChatbotException if input is missing information, or in the wrong format.
     */
    public EventCommand(String input) throws ChatbotException {
        if (input.length() < 7) throw new ChatbotException("missing info lah.");
        String[] parts = input.substring(6).split(" /from | /to ");
        if (parts.length == 3) {
            this.eventName = parts[0];
            this.startDate = parts[1];
            this.endDate = parts[2];
        } else {
            throw new ChatbotException("missing info lah.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Creates new Event and adds it to the TaskList.
     * @param taskList The TaskList to be acted on.
     * @param ui Handles User interaction.
     * @param storage Handles saving the updated TaskList.
     * @throws ChatbotException if error while saving.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        Event temp = new Event(this.eventName, this.startDate, this.endDate);
        taskList.add(temp);
        storage.save(taskList);
        Ui.printAddedTask(temp, taskList.size());
    }
}

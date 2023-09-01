package Remy.command;

import Remy.task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;
import Remy.task.Event;

public class EventCommand extends Command {
    private String eventName;
    private String startDate;
    private String endDate;
    public static final String COMMAND_WORD = "event";

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
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        Event temp = new Event(this.eventName, this.startDate, this.endDate);
        taskList.add(temp);
        storage.save(taskList);
        Ui.printAddedTask(temp, taskList.size());
    }
}

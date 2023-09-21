package avalon.command;

import avalon.AvalonException;
import avalon.task.Event;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

public class EventCommand extends Command {

    private final String userInput;

    public EventCommand(String userInput) {
        this.userInput = userInput;
    }
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        String[] parts = userInput.substring(6).split(" /from | /to ");
        if (parts.length != 3) {
            throw new AvalonException("Please provide a description, a starting time, "
                    + "and an ending time (use /from and /to).");
        }
        String description = parts[0];
        String from = parts[1];
        String to = parts[2];
        Event event = new Event(description, from, to);
        taskList.addTask(event);
        ui.showAddTaskMessage(taskList);
        return ui.getOutput();
    }
}

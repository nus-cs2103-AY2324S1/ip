package avalon.command;

import avalon.AvalonException;
import avalon.task.Event;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

/**
 * Represents a command to add an event task to the task list.
 * This command is triggered by user input starting with "event".
 */
public class EventCommand extends Command {

    private final String userInput;

    /**
     * Constructs an EventCommand with the user input.
     *
     * @param userInput The user input specifying the event details.
     */
    public EventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the EventCommand, adding an event task to the task list.
     *
     * @param taskList The TaskList to which the event task will be added.
     * @param storage  The Storage instance for reading/writing task data.
     * @param ui       The Ui instance for user interaction.
     * @return A string message indicating the result of the command's execution.
     * @throws AvalonException If there is an error while executing the command, such as missing event details.
     */
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

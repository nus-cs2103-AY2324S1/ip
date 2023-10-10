package buddy.commands;
import java.time.LocalDate;

import buddy.Event;
import buddy.TaskList;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for adding an Event into the task list.
 */
public class AddEventCommand extends Command {
    public static final String MESSAGE_FORMAT =
            "event <description> /from <start date> /to <end date>\n"
                    + "Example: event trip to JB /from 2023-09-29 /to 2023-09-30";
    private String description;
    private LocalDate eventStart;
    private LocalDate eventEnd;

    /**
     * The constructor for an AddEventCommand.
     *
     * @param description The description of the event.
     * @param eventStart The start date of the event.
     * @param eventEnd The end date of the event.
     */
    public AddEventCommand(String description, LocalDate eventStart, LocalDate eventEnd) {
        this.description = description;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, eventStart, eventEnd, false);
        tasks.addTask(event);
        String response = ui.printAddSuccessMessage(event, tasks);
        storage.writeToFile(tasks.getAllTasks());
        return response;
    }
}

package dialogix.command;

import java.util.Date;

import dialogix.exception.DialogixException;
import dialogix.main.Storage;
import dialogix.main.TaskList;
import dialogix.main.Ui;
import dialogix.task.Event;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends Command {
    private final String description;
    private String eventAt;
    private Date eventDate;

    /**
     * Constructs an AddEventCommand with a description and an event time in string format.
     *
     * @param description The description of the event task.
     * @param eventAt     The event time in string format.
     */
    public AddEventCommand(String description, String eventAt) {
        this.description = description;
        this.eventAt = eventAt;
    }

    /**
     * Constructs an AddEventCommand with a description and an event time in Date format.
     *
     * @param description The description of the event task.
     * @param eventDate   The event time in Date format.
     */
    public AddEventCommand(String description, Date eventDate) {
        this.description = description;
        this.eventDate = eventDate;
    }

    /**
     * Executes the AddEventCommand by adding an event task to the task list and saving the updated list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DialogixException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        tasks.addToStack();
        Event event = validateEvent();
        tasks.add(event);
        ui.printAddSuccessMessage(event, tasks.size());
        storage.save(tasks.getAllTasks());
    }

    /**
     * Validates and creates an Event object based on the provided information.
     *
     * @return An Event object.
     */
    private Event validateEvent() {
        Event event;
        if (eventDate == null) {
            event = new Event(description, eventAt);
        } else {
            event = new Event(description, eventDate);
        }
        return event;
    }
}


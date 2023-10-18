package commands;

import java.time.LocalDateTime;

import data.TaskList;
import data.exception.InvalidParamException;
import data.exception.StorageException;
import data.tasks.Event;
import data.tasks.Task;
import storage.Storage;
import ui.UiMessage;

/**
 * The EventCommand class.
 * Handles creating a new {@link Event}.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * The constructor method of the EventCommand class.
     * Takes in the description and the two dates of an {@link Event}.
     *
     * @param description The description of an event.
     * @param from The start date of an event.
     * @param to The end date of an event.
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public UiMessage execute(
            TaskList tasks, Storage storage)
        throws StorageException, InvalidParamException {
        Task event = new Event(description, from, to);
        tasks.add(event);
        storage.update(tasks);
        return new UiMessage(new String[] {
            "Okie! I've added a new EVENT:",
            "  " + event,
            "Total no. of tasks stored: " + tasks.getSize()
        });
    }
}

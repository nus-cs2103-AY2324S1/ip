package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.*;

/**
 * Represents a command to add an event to the task list.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new EventCommand object with the given description, start and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command, which attempts to create a new event task with the
     * provided description, start and end time, and add it to the given task list.
     * It also updates the storage after addition.
     *
     * @param tasks   The task list that contains the list of tasks.
     * @param ui      The UI of the application.
     * @param storage The storage that stores task list data.
     * @throws DukeException If the description, start, or end time is invalid.
     * @throws IOException   If there is an error saving the updated task list to storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description, start, or end time of an event cannot be empty.");
        }

        Event newEvent;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
            LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
            newEvent = new Event(description, dateTimeFrom, dateTimeTo);
            tasks.add(newEvent);
            storage.saveTasks(tasks); // Save the updated tasks to file
            return ui.showAddedTask(newEvent, tasks.size());
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date-time format! Please use d/M/yyyy HHmm format.");
        } catch (IOException e) {
            return ui.showSavingError(e.getMessage());
        }
    }
}

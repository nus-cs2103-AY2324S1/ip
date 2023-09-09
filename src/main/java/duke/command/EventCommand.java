package duke.command;

import duke.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

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

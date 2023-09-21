package command;

import exception.DukeException;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task.Event task.
 */
public class AddEvent extends AddCommand {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HHmm";
    private static final String FROM_PREFIX = "/from";
    private static final String TO_PREFIX = "/to";
    private static final int DESCRIPTION_START_INDEX = 6;

    /**
     * Initializes a command.AddEvent command with the specified input.
     *
     * @param input The input string containing task details.
     */
    public AddEvent(String input) {
        super(input);
    }

    /**
     * Executes the command.AddEvent command to add a task.Event task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving tasks to a file.
     * @return A string representation of the task added message and the added task details.
     * @throws DukeException If there is an error in parsing or adding the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        StringBuilder response = new StringBuilder();

        if (!input.contains(FROM_PREFIX) || !input.contains(TO_PREFIX)) {
            throw new DukeException("An Event task should have a '/from' and '/to' with respective times.");
        }

        String[] sections = input.split(FROM_PREFIX + "|" + TO_PREFIX);

        if (sections.length < 3 || sections[0].trim().length() <= DESCRIPTION_START_INDEX ||
                sections[1].trim().isEmpty() || sections[2].trim().isEmpty()) {
            throw new DukeException("Incorrect format for event.");
        }

        String description = sections[0].substring(DESCRIPTION_START_INDEX).trim();
        String from = sections[1].trim();
        String to = sections[2].trim();

        try {
            LocalDateTime formattedFrom = parseDateTime(from);
            LocalDateTime formattedTo = parseDateTime(to);
            Event event = new Event(description, formattedFrom, formattedTo);
            tasks.add(event);
            response.append(ui.showAddedTask(event));
            response.append("\n").append(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date/time format for event. Please use '" + DATE_TIME_PATTERN + "'.");
        }

        return response.toString();
    }

    /**
     * Helper method to parse date and time strings into LocalDateTime objects.
     *
     * @param dateTimeStr The date and time string to parse.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
}

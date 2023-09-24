package command;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task.Deadline task.
 */
public class AddDeadline extends AddCommand {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HHmm";
    private static final String DEADLINE_PREFIX = "/by";
    private static final int DESCRIPTION_START_INDEX = 8;

    /**
     * Creates a command.AddDeadline command.
     *
     * @param input The user input containing task description and deadline.
     */
    public AddDeadline(String input) {
        super(input);
    }

    /**
     * Executes the command.AddDeadline command to add a task.Deadline task.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks to a file.
     * @return The result string after the execution of the command.
     * @throws DukeException If there is an error in the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        StringBuilder response = new StringBuilder();
        if (!input.contains(DEADLINE_PREFIX)) {
            throw new DukeException("A Deadline task should have a '/by' followed by the deadline time.");
        }

        String[] sections = input.split(DEADLINE_PREFIX);

        if (sections.length < 2 || sections[0].trim().isEmpty() || sections[1].trim().isEmpty()) {
            throw new DukeException("Incorrect format for deadline.");
        }

        String description = sections[0].trim().substring(DESCRIPTION_START_INDEX);
        String by = sections[1].trim();

        try {
            LocalDateTime deadlineDateTime = parseDateTime(by);
            Deadline newDeadline = new Deadline(description, deadlineDateTime);
            tasks.add(newDeadline);
            response.append(ui.showAddedTask(newDeadline));
            response.append("\n").append(newDeadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date/time format for deadline. Please use '" + DATE_TIME_PATTERN + "'.");
        }

        return response.toString();
    }

    /**
     * Helper method to parse date and time strings into LocalDateTime objects.
     *
     * @param dateTimeStr The date/time string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
}
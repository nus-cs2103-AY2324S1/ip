package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import storage.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to add a task.Event task.
 */
public class AddEvent extends AddCommand {

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
     * @throws DukeException If there is an error in parsing or adding the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Check if input contains /from and /to
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new DukeException("An task.Event task should have a '/from' and '/to' with respective times.");
            }

            // Split the input into sections
            String[] sections = input.split("/from|/to");

            // Check if there are at least three sections and validate each one
            if (sections.length < 3 || sections[0].trim().length() <= 6 || sections[1].trim().isEmpty() || sections[2].trim().isEmpty()) {
                throw new DukeException("Incorrect date/time for event. Please use 'yyyy-MM-dd HHmm'.");
            }

            // Get description, from, and to parts
            String description = sections[0].substring(6).trim();
            String from = sections[1].trim();
            String to = sections[2].trim();

            // Parse date and time to LocalDateTime
            LocalDateTime formattedFrom = parseDateTime(from);
            LocalDateTime formattedTo = parseDateTime(to);

            // Create and add task.Event task
            tasks.add(new Event(description, formattedFrom, formattedTo));

            // Display message
            ui.showAddedTask(tasks.getList().get(tasks.size() - 1));

        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date/time for event. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    /**
     * Helper method to parse date and time strings into LocalDateTime objects.
     *
     * @param dateTimeStr The date and time string to parse.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));    }
}

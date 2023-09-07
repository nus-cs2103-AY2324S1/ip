package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import task.Deadline;
import task.TaskList;
import storage.Storage;
import ui.Ui;


/**
 * Represents a command to add a task.Deadline task.
 */
public class AddDeadline extends AddCommand {

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
     * @throws DukeException If there is an error in the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // Check if input contains /by
            if (!input.contains("/by")) {
                throw new DukeException("A task.Deadline task should have a '/by' followed by the deadline time.");
            }

            // Split the input into sections
            String[] sections = input.split("/by");

            // Check if there are at least two sections & validate each one
            if (sections.length < 2 || sections[0].trim().isEmpty() || sections[1].trim().isEmpty()) {
                throw new DukeException("Incorrect format for deadline.");
            }

            // Get description and deadline parts
            String description = sections[0].trim().substring(8);
            String by = sections[1].trim();

            // Parse date and time to LocalDateTime
            LocalDateTime deadlineDateTime = parseDateTime(by);

            // Create and add task.Deadline task
            tasks.add(new Deadline(description, deadlineDateTime));

            // Display message
            ui.showAddedTask(tasks.getList().get(tasks.size() - 1));

        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date/time format for deadline. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    /**
     * Helper method to parse date and time strings into LocalDateTime objects.
     *
     * @param dateTimeStr The date/time string to be parsed.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}

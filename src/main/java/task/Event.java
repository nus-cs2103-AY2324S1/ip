package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an "Event" task, which extends the abstract Task class.
 * An "Event" task is a task with a description, a start date,
 * and a due date by which the task should be completed.
 */
public class Event extends Task {
    // A List containing possible formats of date and time.
    private static final List<String> DATE_STRING_FORMATS = Arrays.asList(
            "yyyy-MM-dd HH:mm",
            "dd/MM/yyyy HH:mm",
            "MM-dd-yyyy HH:mm"
            // Add other formats here
    );
    private String startDate;
    private String dueDate;

    /**
     * Constructs an Event instance with a specified task name, start date,
     * and due date, automatically setting its task type to EVENT.
     *
     * @param taskName The name or description of the Event task.
     * @param startDate The start date for the Event task.
     * @param dueDate The due date for the Event task represented as a String.
     */
    public Event(String taskName, String startDate,String dueDate) {
        super(taskName, TaskType.EVENT);
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the Event task, which includes
     * its task type represented as "[E]"
     * followed by its status (done or not done), its name, its start date and its due date.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + dueDate + ")";
    }

    /**
     * Parses a string representation of an Event task back into an Event object.
     * It reads the task's description, completion status, start date
     * and due date from the given line of string and creates a corresponding Event object.
     * It also handles dates formatted in different styles
     * by trying to parse the date in various formats.
     *
     * @param line The string representation of the Event task, typically read from a data file.
     * @return An Event object that corresponds to the data in the given line of string.
     */
    public static Event parseFromString(String line) {
        int firstBracketIndex = line.indexOf(']');
        String description = line.substring(firstBracketIndex + 5).split(" \\(from: | to: ")[0];
        String startDateInput = line.split(" \\(from: | to: |\\) ")[1];
        String dueDateInput = line.split(" \\(from: | to: |\\)")[2];
        String startDateString = null;
        String dueDateString = null;

        // Try parsing with different formats for starting date and time input.
        for (String formatString : DATE_STRING_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                LocalDateTime startDate = LocalDateTime.parse(startDateInput, formatter);
                startDateString = startDate.format(formatter);
                break;  // Stop at the first successful parse
            } catch (DateTimeParseException e) {
                // Ignore the exception and try the next format
            }
        }

        // Try parsing with different formats for due date and time input.
        for (String formatString : DATE_STRING_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                LocalDateTime dueDate = LocalDateTime.parse(dueDateInput, formatter);
                dueDateString = dueDate.format(formatter);
                break;  // Stop at the first successful parse
            } catch (DateTimeParseException e) {
                // Ignore the exception and try the next format
            }
        }

        String mark = line.substring(firstBracketIndex + 2, firstBracketIndex + 3);
        Event task = new Event(description, startDateString, dueDateString);

        // if task is initially marked done, then mark the task as done
        if (mark.equals("X")) {
            task.markDone();
        }

        return task;
    }
}

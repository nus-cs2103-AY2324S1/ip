package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a "Deadline" task, which extends the abstract Task class.
 * A "Deadline" task is a task with a description and a due date by which the task should be completed.
 */
public class Deadline extends Task {
    private String dueDate;

    /**
     * Constructs a Deadline instance with a specified task name and due date, automatically setting its task type to DEADLINE.
     *
     * @param taskName The name or description of the Deadline task.
     * @param dueDate The due date for the Deadline task represented as a String.
     */
    public Deadline(String taskName, String dueDate) {
        super(taskName, TaskType.DEADLINE);
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the Deadline task, which includes its task type represented as "[D]"
     * followed by its status (done or not done), its name, and its due date.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
            return "[D]"+ super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * Parses a string representation of a Deadline task back into a Deadline object.
     * It reads the task's description, completion status, and due date from the given line of string and creates a corresponding Deadline object.
     * It also handles dates formatted in different styles by trying to parse the date in various formats.
     *
     * @param line The string representation of the Deadline task, typically read from a data file.
     * @return A Deadline object that corresponds to the data in the given line of string.
     */
    public static Deadline parseFromString(String line) {
        int firstBracketIndex = line.indexOf(']');
        String description = line.substring(firstBracketIndex + 5).split(" \\(by: ")[0];

        // ArrayList containing possible formats of Date and Time.
        List<String> formatStrings = Arrays.asList(
                "yyyy-MM-dd HH:mm",
                "dd/MM/yyyy HH:mm",
                "MM-dd-yyyy HH:mm"
                // Add other formats here
        );
        String dateInput = line.split("\\(by: |\\)")[1];
        String dueDate = null;

        // Try parsing with different formats
        for (String formatString : formatStrings) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                LocalDateTime dateTime = LocalDateTime.parse(dateInput, formatter);
                dueDate = dateTime.format(formatter);
                break;  // Stop at the first successful parse
            } catch (DateTimeParseException e) {
                // Ignore the exception and try the next format
            }
        }
        String mark = line.substring(firstBracketIndex + 2, firstBracketIndex + 3);
        Deadline task = new Deadline(description, dueDate);

        // if task is initially marked done, then mark the task as done
        if (mark.equals("X")) {
            task.markDone();
        }
        return task;
    }
}

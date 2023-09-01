import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    private String startDate;
    private String dueDate;
    public Event(String taskName, String startDate,String dueDate) {
        super(taskName, TaskType.EVENT);
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + dueDate + ")";
    }

    /**
     * Parse from string to a Event task
     *
     * @param line The String that is needed to parse into a Event Task
     */
    public static Event parseFromString(String line) {
        int firstBracketIndex = line.indexOf(']');
        String description = line.substring(firstBracketIndex + 5).split(" \\(from: | to: ")[0];
        String startDateInput = line.split(" \\(from: | to: |\\) ")[1];
        String dueDateInput = line.split(" \\(from: | to: |\\)")[2];

        // ArrayList containing possible formats of Date and Time.
        List<String> formatStrings = Arrays.asList(
                "yyyy-MM-dd HH:mm",
                "dd/MM/yyyy HH:mm",
                "MM-dd-yyyy HH:mm"
                // Add other formats here
        );
        String startDate = null;

        // Try parsing with different formats for starting date and time input.
        for (String formatString : formatStrings) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                LocalDateTime StartDateTime = LocalDateTime.parse(startDateInput, formatter);
                startDate = StartDateTime.format(formatter);
                break;  // Stop at the first successful parse
            } catch (DateTimeParseException e) {
                // Ignore the exception and try the next format
            }
        }

        String dueDate = null;
        // Try parsing with different formats for due date and time input.
        for (String formatString : formatStrings) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                LocalDateTime dueDateTime = LocalDateTime.parse(dueDateInput, formatter);
                dueDate = dueDateTime.format(formatter);
                break;  // Stop at the first successful parse
            } catch (DateTimeParseException e) {
                // Ignore the exception and try the next format
            }
        }

        String mark = line.substring(firstBracketIndex + 2, firstBracketIndex + 3);
        Event task = new Event(description, startDate, dueDate);

        // if task is initially marked done, then mark the task as done
        if (mark.equals("X")) {
            task.markDone();
        }
        return task;
    }
}

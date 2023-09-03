package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    private String dueDate;
    public Deadline(String taskName, String dueDate) {
        super(taskName, TaskType.DEADLINE);
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
            return "[D]"+ super.toString() + " (by: " + dueDate + ")";
    }

    /**
     * Parse from string to a task.Deadline task
     *
     * @param line The String that is needed to parse into a task.Deadline Task
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

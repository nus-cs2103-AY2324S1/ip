package rayshawn.chatbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline task in tasklist.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructor for Deadline.
     *
     * @param task description of task
     * @param date task due date
     */
    public Deadline(String task, String date) {
        super(task, "D");
        assert date != null : "Date should not be null";
        this.date = LocalDate.parse(date);
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        assert date != null : "Date should not be null";  // Assertion 1
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

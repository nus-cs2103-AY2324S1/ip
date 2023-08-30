package rayshawn.chatbot.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

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
        this.date = LocalDate.parse(date);
    }

   public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
   }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

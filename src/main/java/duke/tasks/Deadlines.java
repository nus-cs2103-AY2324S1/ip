package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with deadlines to meet.
 */
public class Deadlines extends Task {
    // additional deadline given for deadline tasks
    private LocalDateTime deadline;

    /**
     * The constructor for deadline task.
     *
     * @param description The string with the description of task.
     * @param deadline The string with deadline to complete task.
     */
    public Deadlines(String description, String deadline) {
        super(description, 'D');

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime deadLineParsed = LocalDateTime.parse(deadline.trim(), formatter);
        this.deadline = deadLineParsed;
    }

    /**
     * Getter for the deadline.
     *
     * @return the deadline.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Converts the deadline from numbers to words.
     *
     * @return The converted deadline.
     */
    public String getDeadlineInWords() {
        String dayOfWeek = deadline.getDayOfWeek().toString();
        int dayOfMonth = deadline.getDayOfMonth();
        String month = deadline.getMonth().toString();
        int year = deadline.getYear();
        return dayOfWeek + " " + dayOfMonth + " " + month + " " + year;
    }


    /**
     * To string method of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by:%s)", getDeadlineInWords());
    }
}

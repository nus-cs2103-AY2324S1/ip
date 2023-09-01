package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * class Deadline extends class Task which consist of variable for Deadline.
 */
public class Deadlines extends Task{
    private LocalDateTime date;

    /**
     * constructor for class Deadline.
     *
     * @param description string for deadline's description
     * @param date LocalDateTime for date of deadline
     */
    public Deadlines(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + date.format(formatter) + ")";
    }

    /**
     * Read the task and convert it to String to be saved in file.
     *
     * @return string of the Task
     */
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateStr = date.format(formatter);
        String done = isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", done, description, dateStr);
    }


    /**
     * Get detail of the date.
     *
     * @return LocalDateTime of the date
     */
    public LocalDateTime getDateTime() {
        return date;
    }
}

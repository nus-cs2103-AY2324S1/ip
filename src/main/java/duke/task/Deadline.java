package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /*
        The due date and time of the task.
     */
    private LocalDateTime dueDate;

    /**
     * Constructs a Deadline object with the provided description, status, and due date.
     *
     * @param description The description of the task.
     * @param isDone True if the task is done else otherwise.
     * @param dueDate The deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime dueDate) {

        super(description, isDone);
        this.dueDate = dueDate;
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    /**
     * Generates the format of the task to be written into the text file.
     *
     * @return The format of the task in the text file.
     */
    @Override
    public String contentLine() {
        return "D" + super.contentLine() + "/" + this.dueDate.toString();
    }


    /**
     * Overrides the 'toString' method of the parent class with to display the task in different format.
     *
     * @return The appearance of the task in the application.
     */
    @Override
    public String toString() {

        String result = "[D]" + super.toString() + " (by: " + formatDate(this.dueDate) + ")";
        return result;
    }

    /**
     * Convert the date and time provided into another format.
     *
     * @param l The date and time to be changed.
     * @return The date and time in "dd/MMM/yyyy HH:mm" format.
     */
    public String formatDate(LocalDateTime l) {
        return l.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm"));
    }
}

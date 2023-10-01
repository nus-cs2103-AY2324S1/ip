package jarvis.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jarvis.ui.Ui;

/**
 * Represents the "Deadline" task in Jarvis app.
 */
public class Deadline extends Task {

    private LocalDateTime dueDateTime;

    /**
     * Initializes a new instance of the Deadline task.
     *
     * @param title       The title or description of the deadline task.
     * @param dueDateTime The due date and time of the deadline.
     * @param isCompleted A boolean indicating whether the deadline task is completed or not.
     */
    public Deadline(String title, LocalDateTime dueDateTime, boolean isCompleted) {
        super(title, isCompleted);
        this.dueDateTime = dueDateTime;
    }

    @Override
    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    /**
     * Overrides the toString method to provide a custom string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Ui.getDefaultDateTimeFormat());
        String formattedDueDate = dueDateTime.format(formatter);
        return "D | " + super.toString() + " | " + formattedDueDate;
    }
}

package jarvis.tasks;

import jarvis.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the "Deadline" task in Jarvis app.
 */
public class Deadline extends Task {

    private LocalDateTime dueDateTime;

    public Deadline(String title, LocalDateTime dueDateTime, boolean isCompleted) {
        super(title, isCompleted);
        this.dueDateTime = dueDateTime;
    }
    
     /**
     * Overrides the toString method to provide a custom string representation of the Deadline task.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Ui.DATE_TIME_FORMAT);
        String formattedDueDate = dueDateTime.format(formatter);
        return "D | " + super.toString() + " | " + formattedDueDate;
    }
}

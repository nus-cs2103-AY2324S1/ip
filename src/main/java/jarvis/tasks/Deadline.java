package jarvis.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jarvis.Ui;

public class Deadline extends Task {

    private LocalDateTime dueDate;

    public Deadline(String title, LocalDateTime dueDate, boolean isCompleted) {
        super(title, isCompleted);
        this.dueDate = dueDate;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Ui.DATE_TIME_FORMAT);
        String formattedDueDate = dueDate.format(formatter);
        return "D | " + super.toString() + " | " + formattedDueDate;
    }
}

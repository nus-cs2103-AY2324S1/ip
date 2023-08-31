package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDateTime duedate;
    public Deadline(String task, String duedate, boolean done) {
        super(task, done);
        this.duedate = stringToDateObj(duedate);
    }

    private LocalDateTime stringToDateObj(String dateString) {
        return LocalDateTime.parse(dateString, formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.duedate.format(formatter));
    }

    @Override
    public String getTaskFileString() {
        return "D" + " , " + super.getTaskFileString() + " , " + this.duedate.format(formatter);
    }

    @Override
    public void printStart() {
        System.out.println("    This deadline has no start time");
    }

    @Override
    public void printEnd() {
        System.out.println("    This deadline has no start time");
    }

    @Override
    public void printDueDate() {
        System.out.println("    This deadline is due: " + this.duedate.toString());
    }
}

package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public void mark() {
        this.done = true;
        System.out.println(super.line() + "Okay, I have marked this task as completed!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public void unMark() {
        this.done = false;
        System.out.println(super.line() + "Okay, I have marked this task as incomplete!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        String submitDate = "(by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return "[D]" + checkbox + task + " " + submitDate;
    }
}

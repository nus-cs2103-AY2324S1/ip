package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and provides methods to mark and unmark the task as done.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Constructs a Deadline instance with the specified task description and deadline.
     *
     * @param task The description of the task.
     * @param deadline The deadline for the task.
     */
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

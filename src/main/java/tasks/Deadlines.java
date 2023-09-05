package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The `Deadlines` class represents a task with a deadline. It extends the `Task` class
 * and adds functionality to handle deadlines and provide formatted representations of tasks.
 */
public class Deadlines extends Task {
    private LocalDate by;

    /**
     * Constructs a `Deadlines` task.
     *
     * @param name   The name of the task.
     * @param by     The deadline for the task in a string format (MMM d yyyy).
     * @param isDone Whether the task is marked as done or not.
     * @throws DateTimeParseException If there is an error parsing the deadline date.
     */
    public Deadlines(String name, String by, boolean isDone) throws DateTimeParseException {
        super(name, isDone);
        this.by = LocalDate.parse(by.trim());
    }

    /**
     * Returns a formatted string representation of the `Deadlines` task, including its name,
     * status, and deadline.
     *
     * @return A formatted string representing the `Deadlines` task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by : " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Marks the `Deadlines` task as done and prints a message indicating the task is marked as done.
     */
    @Override
    public void markDone() {
        super.markDone();
        System.out.println("Oki, I've marked this task as done: \n" + this.toString());
    }

    /**
     * Unmarks the `Deadlines` task as done and prints a message indicating the task is unmarked as done.
     */
    @Override
    public void unmarkDone() {
        super.unmarkDone();
        System.out.println("Damn bro...unmarked this task :( : \n" + this.toString());
    }
}

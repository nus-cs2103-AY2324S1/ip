package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a specific start and end time.
 * It extends the Task class and provides methods to mark and unmark the task as done.
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructs a Event instance with the specified task description, start time, and end time.
     *
     * @param task The description of the task.
     * @param start The start time for the event.
     * @param end The end time for the event.
     */
    public Event(String task, LocalDate start, LocalDate end) {
        super(task);
        this.start = start;
        this.end = end;
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
        String startTime = "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endTime = "to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return "[E]" + checkbox + task + " " + startTime + " " + endTime;
    }
}

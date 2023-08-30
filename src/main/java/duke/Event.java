package duke;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    protected LocalTime startTime;

    protected LocalTime endTime;

    public Event(String list, String start, String end, String startTime, String endTime, TaskType type) {
        super(list, type);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    /**
     * Marks the task as completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful marking of the task.
     * @throws DukeException If the task has already been marked as done.
     */
    @Override
    public String setMarked() throws DukeException{
        super.setMarked();
        return "Nice! I've marked this task as done:\n" + toString();
    }

    /**
     * Marks the task as not completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful unmarking of the task.
     * @throws DukeException If the task has already been marked as not done.
     */
    @Override
    public String setUnmarked() throws DukeException{
        super.setUnmarked();
        return "OK, I've marked this task as not done yet:\n" + toString();
    }

    @Override
    public String toString() {
        return super.toString() + "(from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.startTime + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.endTime + ")";
    }

}

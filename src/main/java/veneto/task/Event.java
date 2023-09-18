package veneto.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /* fields */
    protected LocalDate start;
    protected LocalDate end;

    /* Constructors */
    /**
     * create a new Event Task
     * @param description the description of the task
     * @param start the start time of the event
     * @param end the end time of the event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * create a new Event task from storage file
     * @param description the description of the task
     * @param mark if the task is marked
     * @param start the start time of the event
     * @param end the end time of the event
     */
    public Event(String description, int mark, String start, String end) {
        super(description, mark != 0);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /* Methods */
    /**
     * explanation of the task
     * @return return task details
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * generate texts for storage
     * @return String of the task data
     */
    @Override
    public String saveToString() {
        return "event," + super.saveToString() + "," + start + "," + end;
    }
}

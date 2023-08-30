package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected String start;

    protected String end;

    protected LocalDate startDate;

    protected LocalDate endDate;

    /**
     * This is a constructor.
     *
     * @param description description of the task.
     * @param start the start time of the in a String format.
     * @param end the end time of the event in a Stirng format.
     */
    public Event (String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event (String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";


    }

    @Override
    public String saveString() {
        return super.saveString() + "/" + startDate.toString() + "/" + endDate.toString();
    }
}

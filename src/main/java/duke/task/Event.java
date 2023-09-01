package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate startDate;

    protected LocalDate endDate;

    /**
     * This is a constructor.
     *
     * @param description Description of the task.
     * @param startDate The start time of the in a LocalDate format.
     * @param endDate The end time of the event in a LocalDate format.
     */
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

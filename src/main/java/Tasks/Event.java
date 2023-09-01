package Tasks;

import java.time.LocalDate;


/**
 * A child class to Task, for this in particular it is for events tasks.
 */
public class Event extends Task{
    private String start;
    private LocalDate startDate;
    private String end;
    private LocalDate endDate;
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
        this.type = "E";
    }

    /**
     * A method that will represent the task.
     *
     * * @return the syntax that will be shown to the user.
     */
    @Override
    public String toString() {
        return super.toString() + "(from: " + (start.isEmpty() ? startDate : start)
                + " to: " + (end.isEmpty() ? endDate : end) + ")";
    }
}

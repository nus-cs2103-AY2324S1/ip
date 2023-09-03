package tasks;

import com.fasterxml.jackson.annotation.JsonTypeName;
import tasks.Task;

/**
 * The Event class represents an event. It has a name, a start and end time.
 */
@JsonTypeName("tasks.Event")
public class Event extends Task {

    public static final String taskType = "E";

    private String startDate;
    private String endDate;

    public Event(String desc, String startDate, String endDate) {
        super(desc);
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public Event() {
        super("");
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

}

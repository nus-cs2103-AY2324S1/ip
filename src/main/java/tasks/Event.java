package tasks;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Event class represents an event. It has a name, a start and end time.
 */
@JsonTypeName("tasks.Event")
public class Event extends Task {

    public static final String TASK_TYPE = "E";

    private String startDate;
    private String endDate;


    /**
     * A event task. It has a start and end date
     *
     * @param desc      users description of the event
     * @param startDate start date as a string.
     * @param endDate   end date as a string.
     */
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

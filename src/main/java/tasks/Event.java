package tasks;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Event class represents an event. It has a name, a start and end time.
 */
@JsonTypeName("tasks.Event")
public class Event extends Task {

    public static final String TASK_TYPE = "E";

    private LocalDateTime startDate;
    private LocalDateTime endDate;


    /**
     * An event task. It has a start and end date
     *
     * @param desc      users description of the event
     * @param startDate start date as a string.
     * @param endDate   end date as a string.
     */
    public Event(String desc, LocalDateTime startDate, LocalDateTime endDate) {
        super(desc);
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public Event() {
        super("");
    }

    /**
     * Checks if the given event conflicts with the current event
     *
     * @param e the event to check for conflicts
     * @return true if there is a conflict. False otherwise
     */
    @JsonIgnore
    public boolean isConflict(Event e) {
        if (startDate.isAfter(e.startDate) && startDate.isBefore(e.endDate) && endDate.isAfter(
            e.endDate)) {
            return true;
        } else if (startDate.isBefore(e.startDate) && endDate.isAfter(e.endDate)) {
            return true;
        } else if (startDate.isBefore(e.startDate) && endDate.isAfter(e.startDate)) {
            return true;
        } else if (startDate.isAfter(e.startDate) && endDate.isBefore(e.endDate)) {
            return true;
        }
        return false;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getStartDateName() {
        return this.startDate.toString();
    }

    public String getEndDateName() {
        return this.endDate.toString();
    }

}

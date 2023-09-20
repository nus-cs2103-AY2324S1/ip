package duke.task;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Event class represents a task without a specific deadline or time.
 * It inherits from the Task class and provides methods to create and display an Event task.
 */
public class Event extends Task {

    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;
    private DayOfWeek fromDay;
    private DayOfWeek toDay;


    /**
     * Constructs an Event task.
     *
     * @param description The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        assert description != null : "Description cannot be null";
        assert from != null : "Start Time cannot be null";
        assert to != null : "End Time cannot be null";

        if (from.contains(",")) {
            String [] splitFrom = from.split(",");
            fromDate = super.convertStringToDate(splitFrom[0]);
            fromDay = super.convertStringToDay(splitFrom[1]);
            if (splitFrom.length == 3) {
                fromTime = super.convertStringToTime(splitFrom[2]);
            }
        } else {
            fromDate = super.convertStringToDate(from);
        }

        if (to.contains(",")) {
            String [] splitTo = to.split(",");
            toDate = super.convertStringToDate(splitTo[0]);
            toDay = super.convertStringToDay(splitTo[1]);
            if (splitTo.length == 3) {
                toTime = super.convertStringToTime(splitTo[2]);
            }
        } else {
            toDate = super.convertStringToDate(to);
        }
    }

    /**
     * Constructs an Event task when reloading tasks from storage file.
     *
     * @param description The description of the Event task.
     * @param from The start time of the Event task.
     * @param to The end time of the Event task.
     * @param status A string indicating the status of the task ("Y" for done, "N" for not done).
     */
    public Event(String description, String from, String to, String status) {
        super(description);
        assert description != null : "Description cannot be null";
        assert from != null : "Start Time cannot be null";
        assert to != null : "End Time cannot be null";
        assert status != null : "Status cannot be null";


        if (from.contains(",")) {
            String [] splitFrom = from.split(", ");
            fromDate = super.convertStringToDate(splitFrom[0]);
            fromDay = super.convertStringToDay(splitFrom[1]);
            if (splitFrom.length == 3) {
                fromTime = super.convertStringToTime(splitFrom[2]);
            }
        } else {
            fromDate = super.convertStringToDate(from);
        }

        if (to.contains(",")) {
            String [] splitTo = to.split(", ");
            toDate = super.convertStringToDate(splitTo[0]);
            toDay = super.convertStringToDay(splitTo[1]);
            if (splitTo.length == 3) {
                toTime = super.convertStringToTime(splitTo[2]);
            }
        } else {
            toDate = super.convertStringToDate(to);
        }

        if (status.equals("Y")) {
            super.taskStatusFromFile(true);
        } else {
            super.taskStatusFromFile(false);
        }
    }


    /**
     * Converts the task to a formatted string representation to be displayed to users.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {

        String fromDate = dateToString(this.fromDate);
        String toDate = dateToString(this.toDate);
        String fromDay = dayToString(this.fromDay);
        String toDay = dayToString(this.toDay);
        String fromTime = timeToString(this.fromTime);
        String toTime = timeToString(this.toTime);

        return "[E]" + super.toString() + " (from:" + fromDate + fromDay
                + fromTime + " to:" + toDate + toDay + toTime + ")";

    }

    /**
     * Converts the task to a formatted string representation for file storage.
     *
     * @return The formatted string representation of the task for file storage.
     */
    @Override
    public String toFileString() {
        String fromDate = dateToString(this.fromDate);
        String toDate = dateToString(this.toDate);
        String fromDay = dayToString(this.fromDay);
        String toDay = dayToString(this.toDay);
        String fromTime = timeToString(this.fromTime);
        String toTime = timeToString(this.toTime);
        return "E" + super.toFileString() + "|" + fromDate + fromDay + fromTime + "|" + toDate + toDay + toTime;
    }
}

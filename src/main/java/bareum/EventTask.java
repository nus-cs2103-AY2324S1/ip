package bareum;

/**
 * This class implements an event with a start and end time.
 */

public class EventTask extends Task {
    /**
     * The start time of the event.
     */
    private String startDateTime;
    /**
     * The end time of the event.
     */
    private String endDateTime;

    /**
     * Creates a new event using the input completion status, description, start date and time and, end date and Time.
     * @param isDone Completion status of the event.
     * @param description Description of the event.
     * @param startDateTime Start time of the event.
     * @param endDateTime End time of the event.
     */
    private EventTask(boolean isDone, String description, String startDateTime,
                     String endDateTime) {
        super(isDone, description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        // add exceptions for time
    }

    /**
     * Creates a new uncompleted event using the description, start and end date and time inputs from the user.
     * @param description Description of the event.
     * @param startDateTime Start time of the event.
     * @param endDateTime End time of the event.
     * @return New uncompleted event using the description, start and end date and time inputs from the user.
     */
    public static EventTask makeEvent(String description, String startDateTime,
                                      String endDateTime) {
        return new EventTask(false, description, startDateTime, endDateTime);
    }

    /**
     * Creates a new event using the inputs from a saved event.
     * @param taskInputs Type, completion status, description and start and end date and time of the saved task.
     * @return New event with the corresponding completion status, description and start and end date and time.
     */
    public static EventTask makeEvent(String[] taskInputs) {
        boolean isDone = taskInputs[1].equals("1");
        String description = taskInputs[2];
        String startDateTime = taskInputs[3];
        String endDateTime = taskInputs[4];
        return new EventTask(isDone, description, startDateTime, endDateTime);
    }

    public String getStartDateTime() {
        return this.startDateTime;
    }

    public String getEndDateTime() {
        return this.endDateTime;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
                + "(from:" + startDateTime + "to:" + endDateTime + ")";
    }

    /**
     * Create a string representation of the details of the event for saving into the hard disk.
     * @return String representation of the details of the event.
     */
    @Override
    public String toSavedString() {
        String done = isDone ? "1" : "0";
        return "E|" + done + "|" + this.description + "|" + this.startDateTime + "|" + this.endDateTime +"\n";
    }
}

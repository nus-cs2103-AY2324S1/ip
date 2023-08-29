/**
 * Represents an event task with a specified start and end date.
 * An Events object is described by a description, start date, and end date.
 */
public class Events extends Task {
    private final String startDate;
    private final String endDate;

    /**
     * Initializes a new instance of the Events class with the given description, start date, and end date.
     *
     * @param description Description of the event.
     * @param startDate Start date of the event.
     * @param endDate End date of the event.
     * @throws DukeException if the provided description, start date, or end date is null or empty.
     */
    public Events(String description, String startDate, String endDate) throws DukeException {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new DukeException("description of event cannot be empty");
        }
        if (startDate == null || startDate.trim().isEmpty()) {
            throw new DukeException("start date of event cannot be empty");
        }
        if (endDate == null || endDate.trim().isEmpty()) {
            throw new DukeException("end date of event cannot be empty");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Events(String description, String startDate, String endDate, boolean completed) throws DukeException {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new DukeException("description of event cannot be empty");
        }
        if (startDate == null || startDate.trim().isEmpty()) {
            throw new DukeException("start date of event cannot be empty");
        }
        if (endDate == null || endDate.trim().isEmpty()) {
            throw new DukeException("end date of event cannot be empty");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = completed;
    }

    /**
     * Retrieves the start date of the event.
     *
     * @return Start date of the event.
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Retrieves the end date of the event.
     *
     * @return End date of the event.
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * Gets the type of the task.
     *
     * @return A string indicating the type of the task.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Gets a formatted string representation of the Events task.
     *
     * @return A string representing the Events task.
     */
    @Override
    public String toString() {
        String completed = this.isCompleted() ? "[X] " : "[ ] ";
        String taskType = "[" + this.getType() + "]";
        String fromMessage = "from: " + getStartDate();
        String toMessage = "to: " + getEndDate();
        return taskType + completed + this.getDescription() + "(" + fromMessage + " " + toMessage + ")";
    }
}

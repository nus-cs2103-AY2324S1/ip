package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a subclass of Task class.
 */
public class Event extends Task {

    private String startTime;
    private String endTime;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    DateTimeFormatter outputFormatWithTime = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");

    /**
     * Constructor for Event class.
     *
     * @param description User provided description.
     * @param startTime User provided start time.
     * @param endTime User provided end time.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Overloaded Constructor for Event class.
     *
     * @param description User provided description.
     * @param startDateTime User provided start time as a LocalDateTime object.
     * @param endDateTime User provided end time as a LocalDateTime object.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * storeFormat() method.
     *
     * @return The specific format in which the task should be saved in the data file.
     */
    @Override
    public String outputStoreFormat() {
        String taskType = "E";
        String isTaskDone;

        if (this.isDone) {
            isTaskDone = "1";
        } else {
            isTaskDone = "0";
        }

        if (startDateTime == null) {
            return (taskType + " | " + isTaskDone + " | " + this.description.trim() + " | " + this.startTime + " | " +
                    this.endTime);
        } else {
            return (taskType + " | " + isTaskDone + " | " + this.description.trim() + " | "
                    + this.startDateTime.format(outputFormatWithTime) + " | " +
                    this.endDateTime.format(outputFormatWithTime));
        }

    }

    /**
     * toString() method.
     *
     * @return String text of the task in which the task will be displayed in the terminal.
     */
    @Override
    public String toString() {
        if (startDateTime == null) {
            return String.format("[E]%s (from: %s to: %s)", super.toString(), startTime, endTime);
        } else {
            return String.format("[E]%s (from: %s to: %s)", super.toString(), startDateTime.format(outputFormatWithTime)
                    , endDateTime.format(outputFormatWithTime));
        }
    }
}

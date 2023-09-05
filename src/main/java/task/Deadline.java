package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class is a subclass of Task class.
 */
public class Deadline extends Task {

    private String time;
    private LocalDateTime dateTime;

    DateTimeFormatter outputFormatWithTime = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");

    /**
     * Constructor for Deadline class.
     * @param description user provided description.
     * @param time user provided description.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Overloaded Constructor for Deadline class.
     * @param description user provided description.
     * @param dateTime user provided end time as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * storeFormat() method.
     * @return the specific format in which the task should be saved in the data file.
     */
    @Override
    public String outputStoreFormat() {
        String taskType = "D";
        String isTaskDone;

        if (this.isDone) {
            isTaskDone = "1";
        } else {
            isTaskDone = "0";
        }

        if (this.dateTime == null) {
            return (taskType + " | " + isTaskDone + " | " + this.description.trim() + " | " + this.time);
        } else {
            return (taskType + " | " + isTaskDone + " | " + this.description.trim() + " | " +
                    this.dateTime.format(outputFormatWithTime));
        }

    }

    /**
     * toString() method.
     * @return string text of the task in which the task will be displayed in the terminal.
     */
    @Override
    public String toString() {
        if (dateTime == null) {
            return String.format("[D]%s (by: %s)", super.toString(), time);
        } else {
            return String.format("[D]%s (by: %s)", super.toString(), dateTime.format(outputFormatWithTime));
        }
    }
}



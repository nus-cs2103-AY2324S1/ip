package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String time;
    private LocalDateTime dateTime;

    DateTimeFormatter outputFormatWithTime = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String storeFormat() {
        String taskType = "D";
        String isTaskDone;

        if (this.isDone) {
            isTaskDone = "1";
        } else {
            isTaskDone = "0";
        }

        if (this.dateTime == null) {
            return (taskType + " | " + isTaskDone + " | " + this.description + " | " + this.time);
        } else {
            return (taskType + " | " + isTaskDone + " | " + this.description + " | " +
                    this.dateTime.format(outputFormatWithTime));
        }

    }

    @Override
    public String toString() {
        if (dateTime == null) {
            return String.format("[D]%s (by: %s)", super.toString(), time);
        } else {
            return String.format("[D]%s (by: %s)", super.toString(), dateTime.format(outputFormatWithTime));
        }
    }
}



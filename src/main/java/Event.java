import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String startTime;
    private String endTime;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    DateTimeFormatter outputFormatWithTime = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        System.out.println("Got it. I've added this task:");
    }

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        System.out.println("Got it. I've added this task:");
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

        return (taskType + " | " + isTaskDone + " | " + this.description + " | " + this.startTime + " | " +
                this.endTime);
    }

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

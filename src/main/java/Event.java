import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }


    @Override
    public String toFileString() {
        char taskType = 'E';
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return taskType + " | " + super.toFileString() + " | " + from.format(formatter) + " | " + to.format(formatter);
    }


    public static Event createEventFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 4 && taskParts[0].trim().equals("E")) {
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime from = LocalDateTime.parse(taskParts[3].trim(), formatter);
            LocalDateTime to = LocalDateTime.parse(taskParts[4].trim(), formatter);

            Event event = new Event(description, from, to);
            if (doneStatus.equals("1")) {
                event.markDone();
            }
            return event;
        }
        return null; // incomplete data.txt
    }

}

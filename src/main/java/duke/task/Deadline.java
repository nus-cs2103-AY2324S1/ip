package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D"+ " | " + super.toFileString() + " | " + by.format(formatter);
    }

    public static Deadline createDeadlineFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 3 && taskParts[0].trim().equals("D")) {
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime by = LocalDateTime.parse(taskParts[3].trim(), formatter);

            Deadline deadline = new Deadline(description, by);
            if (doneStatus.equals("1")) {
                deadline.markDone();
            }
            return deadline;
        }
        return null; // incomplete data.txt
    }
}

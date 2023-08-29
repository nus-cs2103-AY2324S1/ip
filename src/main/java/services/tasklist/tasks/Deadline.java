package services.tasklist.tasks;

import services.bizerrors.InvalidArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime endTime;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String endTime) throws InvalidArgumentException {
        super(description);
        try {
            this.endTime = LocalDateTime.parse(endTime, inputFormatter);
        } catch (Exception e) {
            throw new InvalidArgumentException("deadline");
        }
    }

    @Override
    public String encode() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime.format(outputFormatter) + ")";
    }
}

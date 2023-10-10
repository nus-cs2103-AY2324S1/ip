package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;
    protected String byDateTimeString;

    public Deadline(String description, boolean isDone, String byDateAndTime) {
        super(description, isDone);
        parseDateTime(byDateAndTime);
        this.byDateTimeString = byDateAndTime;
    }

    public LocalDate getDate() {
        return this.byDate;
    }

    public boolean isValidDate(String dateTimeString) {
        try {
            String[] parts = dateTimeString.split(" ");
            LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime.parse(parts[1], DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void parseDateTime(String dateTime) {
        if (isValidDate(dateTime)) {
            String[] parts = dateTime.split(" ");
            byDate = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            byTime = LocalTime.parse(parts[1], DateTimeFormatter.ofPattern("HH:mm"));
        }

    }

    @Override
    public String toString() {
        if (byDate != null) {
            return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    + " " + byTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + byDateTimeString + ")";
        }

    }

    public String toSaveString() {
        if (byDate != null) {
            return "D," + (isDone ? "1" : "0") + "," + description + "," + byDate + " " + byTime;
        } else {
            return "D," + (isDone ? "1" : "0") + "," + description + "," + byDateTimeString;
        }

    }
}

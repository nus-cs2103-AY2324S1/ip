package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected String[] parts;

    protected String date;
    protected String time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void dateTime() {
        parts = this.by.split("/");
        String day;
        String month;
        String year;

        if (parts.length != 1) {

            if (parts[0].length() == 1) {
                day = "0" + parts[0];
            } else {
                day = parts[0];
            }

            if (parts[1].length() == 1) {
                month = "0" + parts[1];
            } else {
                month = parts[1];
            }

            String[] yearTime = parts[2].split("\\s+");

            if (yearTime.length == 1) {
                year = parts[2];
            } else {
                year = yearTime[0];
                time = yearTime[1].substring(0, 2) + ":" + yearTime[1].substring(2);
            }

            date = year + "-" + month + "-" + day;
        }
    }
    @Override
    public String saveTask() {
        return "D" + " | " + super.saveTask() + " | " + this.by;
    }

    @Override
    public String toString() {
        dateTime();

        if (parts.length == 1) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            LocalDate deadline = LocalDate.parse(date);
            if (time == null) {
                return "[D]" + super.toString() + " (by: " +
                        deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            } else {
                LocalTime deadlineTime = LocalTime.parse(time);
                return "[D]" + super.toString() + " (by: " +
                        deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " +
                        deadlineTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
            }
        }
    }
}

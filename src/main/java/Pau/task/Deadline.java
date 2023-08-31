package Pau.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {
    protected String deadline;
    protected LocalDate deadlineDate;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        if (isValidDateFormat(deadline, "yyyy-MM-dd")) {
            this.deadlineDate = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.deadline = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    @Override
    public String writeToFile() {
        String delimiter = " | ";
        String status = this.isDone ? "1" : "0";
        return "D" + delimiter + status + delimiter + this.description + delimiter + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    public static boolean isValidDateFormat(String deadline, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            formatter.parse(deadline);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

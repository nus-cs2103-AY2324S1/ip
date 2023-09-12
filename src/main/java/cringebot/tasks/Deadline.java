package cringebot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to create a deadline for the user.
 */
public class Deadline extends Task {
    private LocalDate date;
    private boolean hasDate = false;

    /**
     * Constructor for Deadline.
     *
     * @param name label for the deadline.
     * @param date Date inputted by the /by command.
     */
    public Deadline(String name, String date) {
        super(name);
        try {
            this.date = LocalDate.parse(date.strip());
            this.hasDate = true;
        } catch (DateTimeParseException e) {
            super.editName(String.format(" (by: %s)", date));
        }
    }

    /**
     * Returns string representation of deadline, including marked or not, date of
     * deadline and contents.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        String checkBox = super.isMarked() ? "[X]" : "[ ]";
        char taskType1 = 'D';
        String taskType = String.format("[%c]", taskType1);
        if (hasDate) {
            return String.format(
                    "%s%s %s (by: %s)",
                    taskType, checkBox, super.getName(), this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        }
        return String.format("%s%s %s", taskType, checkBox, super.getName());
    }
}
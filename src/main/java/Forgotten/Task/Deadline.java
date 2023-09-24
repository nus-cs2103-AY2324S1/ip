package Forgotten.Task;

import Forgotten.Priority;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate day;


    private Deadline(String description, LocalDate day) {
        super(description);
        this.day = day;
    }

    /**
     * Default constructor method.
     *
     * @param description Description of this deadline task.
     * @param day The date this task is due.
     * @param isDone Status of this task, either done or not done.
     */
    public Deadline(String description, LocalDate day, boolean isDone, Priority priority) {
        super(description);
        this.day = day;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * This is a factory method which generates an Deadline task.
     *
     * @param description Description of this Deadline task.
     * @return The Deadline task.
     */
    public static Deadline createNewDeadlineTask(String description) throws DateTimeParseException {
        String[] splitMessage = description.split(" /by ");
        LocalDate date = LocalDate.parse(splitMessage[1]);
        return new Deadline(splitMessage[0], date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.day.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                + " [P: " + this.priority + "]";
    }

    /**
     * This method returns the string representation of this Deadline task
     * in a format which can be stored in the hard disk.
     *
     * @return The string representation of this Deadline task.
     */
    @Override
    public String toFileString() {
        return "[D]" + super.toString() + " (by: " + this.day + ")"
                + " [P: " + this.priority + "]";
    }
}

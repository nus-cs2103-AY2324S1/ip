package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime due;

    /**
     * Constructs a new {@code Deadline} object, with {@code isCompleted} set to false.
     *
     * @param details Details of the {@code Deadline}.
     * @param due Due date and time of the {@code Deadline}, stored as a {@code LocalDateTime} object.
     */
    public Deadline(String details, LocalDateTime due) {
        super(details);
        this.due = due;
    }

    /**
     * Loads a {@code Deadline} object that was previously stored in the hard disk.
     * The {@code isCompleted} parameter corresponds to the completion status in the last
     * instance of {@code Duke}.
     *
     * @param details Details of the {@code Deadline}.
     * @param isCompleted Completion status of the {@code Deadline}.
     * @param due Due date and time of the {@code Deadline}, stored as a {@code LocalDateTime} object.
     */
    public Deadline(String details, boolean isCompleted, LocalDateTime due) {
        super(details, isCompleted);
        this.due = due;
    }

    /**
     * Returns the string representation of the {@code Deadline}, to be
     * printed in the {@code list} method.
     *
     * @return String representation of the {@code Deadline}.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "[D]" + super.toString() + " (by: " +
                this.due.toLocalDate().format(dateFormatter) + " " +
                this.due.toLocalTime().format(timeFormatter) + ")";
    }
}

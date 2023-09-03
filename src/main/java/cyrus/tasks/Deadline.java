package cyrus.tasks;

import java.time.LocalDate;

import com.google.gson.annotations.SerializedName;

import cyrus.utility.DateUtility;

/**
 * Deadline task that contains the name of the deadline and the due date (i.e. {@code deadline}.
 */
public class Deadline extends Task {
    @SerializedName("type")
    private static final String TYPE = "deadline";
    @SerializedName("due")
    private final LocalDate deadline;

    /**
     * Create Deadline with name and deadline date.
     *
     * @param name     name of deadline task.
     * @param deadline date of deadline.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), DateUtility.formatLocalDate(this.deadline));
    }
}

package chatterbot.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        if (description.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! Invalid input! No ttask description.");
        }
    }

    /**
     * Returns the description and deadline of the deadline task.
     * @return String This is the deadline description and deadline in the format it will be displayed in, in the list.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + modifyBy(by) + ")";
    }

    /**
     * Returns the modified version of the deadline in a MMM d yyyy format.
     * @param by This is the deadline.
     * @return String This is the formatted deadline.
     */
    public String modifyBy(String by) {
        LocalDate ld = LocalDate.parse(by);
        return ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the input for the Chatterbot.txt file in the same format as was entered by the user.
     * @return String This is the formatted line to add to the ChatterBot.txt file.
     */
    @Override
    public String forFile() {
        return "deadline " + this.description + " /by " + modifyBy(by);
    }
}
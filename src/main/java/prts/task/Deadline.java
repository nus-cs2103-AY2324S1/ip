package prts.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a due date.
 * The date can be parsed if input in the form Day-Month-Year, with a short Month form.
 * If the date cannot be parsed, stores the user-input string as the due date.
 */
public class Deadline extends Task {

    private String description;
    private String addMessage = "Alright. I'll make sure you don't forget it.";
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline object given the description and due date, in the form of the user input.
     * @param name The description of the deadline.
     * @param description The due date of the task.
     */
    public Deadline(String name, String description) {
        super(name);
        this.description = description;
    }

    /**
     * Constructs a Deadline object given the description and a due date.
     * @param name The description of the deadline.
     * @param deadline The due date of the task.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the Deadline object, for display to the user.
     * @return the string representation of the deadline.
     */
    @Override
    public String toString() {
        String detail = description == null
                ? deadline.format(DateTimeFormatter.ofPattern("hh':'mma',' d MMM uuuu',' eee"))
                : description;
        return "[D]" + super.toString() + " (by: " + detail + ")";
    }

    /**
     * Returns the message that should be displayed to the user upon addition of the Deadline to
     * the TaskList.
     * @return the message displayed to the user.
     */
    public String getAddMessage() {
        return addMessage;
    }

}

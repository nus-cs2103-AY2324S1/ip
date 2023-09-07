package brandon.chatbot.tasks;

import brandon.chatbot.common.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task with Deadline date.
 */
public class Deadline extends Task {

    private String deadline;

    /**
     * Validates the parameters and creates a Deadline object.
     *
     * @param title of the Deadline task.
     * @param deadline of the Deadline task.
     * @throws DukeException if the parameters are invalid.
     */
    public Deadline(String title, String deadline) throws DukeException {
        super(title);
        if (deadline.isBlank()) {
            throw new DukeException("    Deadline cannot be blank...\n--------------------------------");
        }
        try {
            String dateFormat = "yyyy-MM-dd";
            LocalDate d1 = LocalDate.parse(deadline, DateTimeFormatter.ofPattern(dateFormat));
            this.deadline = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Could you try your date in yyyy-mm-dd format instead...?");
        }
    }

    @Override
    public String toString() {
        return "Deadline";
    }
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.deadline + ")";
    }


}

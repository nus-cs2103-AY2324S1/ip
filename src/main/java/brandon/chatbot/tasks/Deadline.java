package brandon.chatbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;

import brandon.chatbot.tag.Tag;
import brandon.chatbot.common.DukeException;

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
    public Deadline(String title, String deadline, Optional<ArrayList<Tag>> tags) throws DukeException {
        super(title, tags);
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
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.deadline + ")";
    }


}

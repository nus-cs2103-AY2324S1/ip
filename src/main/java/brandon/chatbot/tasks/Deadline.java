package brandon.chatbot.tasks;

import static brandon.chatbot.commands.Feedback.DEADLINE_BLANK;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;

/**
 * Represents a Task with Deadline date.
 */
public class Deadline extends Task {
    private String deadlineNotFormatted;
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
        if (deadline == null) {
            throw new DukeException(DEADLINE_BLANK);
        }
        try {
            this.deadlineNotFormatted = deadline;

            String inputDateFormat = "yyyy-MM-dd";
            String outputDateFormat = "MMM d yyyy";
            LocalDate d1 = LocalDate.parse(deadline.strip(), DateTimeFormatter.ofPattern(inputDateFormat));
            this.deadline = d1.format(DateTimeFormatter.ofPattern(outputDateFormat, Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            String wrongDateInputExceptionMessage = "Could you try your date in yyyy-mm-dd format instead...?";
            throw new DukeException(wrongDateInputExceptionMessage);
        }
    }

    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.deadline + ")";
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String getOtherData() {
        return "/by: " + this.deadlineNotFormatted;
    }
}

package brandon.chatbot.tasks;

import static brandon.chatbot.commands.Feedback.ENDING_TIME_BLANK;
import static brandon.chatbot.commands.Feedback.STARTING_TIME_BLANK;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tag.Tag;


/**
 * Represents an Event Task with starting time and end time.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs event with title, starting time, and end time.
     * @param title of the event.
     * @param startTime of the event.
     * @param endTime of the event.
     * @throws DukeException if the parameters are invalid.
     */
    public Event(String title, String startTime, String endTime, Optional<ArrayList<Tag>> tags) throws DukeException {
        super(title, tags);
        if (startTime == null) {
            throw new DukeException(STARTING_TIME_BLANK);
        }
        if (endTime == null) {
            throw new DukeException(ENDING_TIME_BLANK);

        }
        try {
            String inputDateFormat = "yyyy-MM-dd";
            String outputDateFormat = "MMM d yyyy";

            LocalDate startDate = LocalDate.parse(startTime.strip(), DateTimeFormatter.ofPattern(inputDateFormat));
            LocalDate endDate = LocalDate.parse(endTime.strip(), DateTimeFormatter.ofPattern(inputDateFormat));
            this.startTime = startDate.format(DateTimeFormatter.ofPattern(outputDateFormat));
            this.endTime = endDate.format(DateTimeFormatter.ofPattern(outputDateFormat));
        } catch (DateTimeParseException e) {
            String wrongDateInputExceptionMessage = "Could you try your date in yyyy-mm-dd format instead...?";
            throw new DukeException(wrongDateInputExceptionMessage);
        }
    }

    public String getStatus() {
        return "[E]" + super.getStatus() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}

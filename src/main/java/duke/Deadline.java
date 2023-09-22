package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a task of type Deadline.
 */
public class Deadline extends TaskType {

    private LocalDateTime dl;

    /**
     * Constructor for the Deadline class.
     *
     * @param task Description of task
     * @param isCompleted Whether of not the task has been completed
     * @param dateString String of datetime related information
     * @throws DukeException when the /by description is empty or cannot be interpreted by given formatters.
     */

    public Deadline(String task, boolean isCompleted, String dateString)
            throws DukeException {
        super(task, isCompleted, dateString);
        String[] s = dateString.split("\\s+");
        String byDateString = Utils.splitStringBySpaces(s, 1, s.length);
        if (byDateString.isEmpty()) {
            throw new DukeException("/by description cannot be empty");
        }
        dl = DtFormat.parseDateString(byDateString);
        if (dl == null) {
            throw new DukeException("/by date format count not be recognized");
        }

    }

    public String toShortString() {
        return "D";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "(by: " + dl.format(formatter) + ")";
    }
}

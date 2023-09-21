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
     * @param formatters List of Datetime formatters used to convert the dateString into a LocalDateTime
     * @throws DukeException when the /by description is empty or cannot be interpreted by given formatters.
     */

    public Deadline(String task, boolean isCompleted, String dateString, ArrayList<DateTimeFormatter> formatters)
            throws DukeException {
        super(task, isCompleted, dateString);
        String[] s = dateString.split("\\s+");
        String a = Utils.splitStringBySpaces(s, 1, s.length);
        if (a.isEmpty()) {
            throw new DukeException("/by description cannot be empty");
        }
        for (DateTimeFormatter fr : formatters) {
            try {
                this.dl = LocalDateTime.parse(a, fr);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }

        }
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

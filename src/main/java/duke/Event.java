package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a task of type Event.
 */
public class Event extends TaskType {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for the Event class.
     *
     * @param task Description of task
     * @param isCompleted Whether of not the task has been completed
     * @param dateString String of datetime related information
     * @param formatters List of Datetime formatters used to convert the dateString into a LocalDateTime
     * @throws DukeException when either the /to or /from description is empty
     */
    public Event(String task, boolean isCompleted, String dateString, ArrayList<DateTimeFormatter> formatters)
            throws DukeException {
        super(task, isCompleted, dateString);
        String[] s = dateString.split("\\s+");
        boolean wasToDetected = false;
        for (int i = 1; i < s.length; i++) {
            if (s[i].equals("/to")) {
                String a = Utils.splitStringBySpaces(s, 1, i);
                String b = Utils.splitStringBySpaces(s, i + 1, s.length);
                if (a.isEmpty()) {
                    throw new DukeException("/from description cannot be empty");
                }
                if (b.isEmpty()) {
                    throw new DukeException("/to description cannot be empty");
                }
                for (DateTimeFormatter fr : formatters) {
                    try {
                        this.from = LocalDateTime.parse(a, fr);
                        break;
                    } catch (DateTimeParseException e) {
                        continue;
                    }
                }
                for (DateTimeFormatter fr : formatters) {
                    try {
                        this.to = LocalDateTime.parse(b, fr);
                        break;
                    } catch (DateTimeParseException e) {
                        continue;
                    }
                }

                wasToDetected = true;
            }
        }
        if (from == null) {
            throw new DukeException("/from date format could not be recognized");
        }
        if (to == null) {
            throw new DukeException("/to date format could not be recognized");
        }
        if (!wasToDetected) {
            throw new DukeException("keyword /to was not detected.");
        }
    }

    public String toShortString() {
        return "E";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "(from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}


package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     * @throws DukeException when either the /to or /from description is empty
     */
    public Event(String task, boolean isCompleted, String dateString)
            throws DukeException {
        super(task, isCompleted, dateString);
        String[] s = dateString.split("\\s+");
        boolean wasToDetected = false;
        for (int i = 1; i < s.length; i++) {
            if (s[i].equals("/to")) {
                String fromDateStr = Utils.splitStringBySpaces(s, 1, i);
                String toDateStr = Utils.splitStringBySpaces(s, i + 1, s.length);
                if (fromDateStr.isEmpty()) {
                    throw new DukeException("/from description cannot be empty");
                }
                if (toDateStr.isEmpty()) {
                    throw new DukeException("/to description cannot be empty");
                }
                this.from = DtFormat.parseDateString(fromDateStr);
                this.to = DtFormat.parseDateString(toDateStr);
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


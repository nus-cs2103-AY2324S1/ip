package duke.tasks;

import duke.exceptions.IncompleteDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class encapsulating information of a task of Event type.
 */
public class Event extends Task {
    private String from;
    private String to;
    private LocalDate fromLd;
    private LocalDate toLd;

    public Event(String taskName, String from, String to) throws IncompleteDescriptionException {
        super(taskName);
        try {
            this.fromLd = LocalDate.parse(from);
            this.toLd = LocalDate.parse(to);
        } catch (DateTimeParseException ignored) {}
        this.from = from;
        this.to = to;
    }

    /**
     * Factory method for the event class.
     *
     * @param taskDesc Description of the event task.
     * @return An Event task object.
     * @throws IncompleteDescriptionException If the task name or the task description is blank or incomplete.
     */
    public static Event create(String taskDesc) throws IncompleteDescriptionException {
        String[] tmp = taskDesc.split(" /from ");
        if (tmp.length <= 1) {
            throw new IncompleteDescriptionException();
        }
        String taskName = tmp[0];
        String[] tmp2 = tmp[1].split(" /to ");
        if (tmp2.length <= 1) {
            throw new IncompleteDescriptionException();
        }
        String from = tmp2[0];
        String to = tmp2[1];
        if (taskName.isBlank() || from.isBlank() || to.isBlank()) {
            throw new IncompleteDescriptionException();
        }
        return new Event(taskName, from, to);
    }

    @Override
    public String compressData() {
        char isDoneChar = this.isDone ? '1' : '0';
        return "E" + " | " + isDoneChar + " | " + this.taskName + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        if (this.fromLd != null && this.toLd != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            return "[E]" + super.toString() + " (from: " + fromLd.format(formatter)
                    + " to: " + toLd.format(formatter) + ")";
        }
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
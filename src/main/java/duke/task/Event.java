package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTextArea;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an event task with description and date-time range.
     *
     * @param description Description of the event.
     * @param from Starting date and time of the event.
     * @param to Ending date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the starting date and time of the event.
     *
     * @return Starting date and time.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the ending date and time of the event.
     *
     * @return Ending date and time.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Converts the event task into a string and displays it in the given JTextArea.
     *
     * @param chatArea JTextArea where the message will be displayed.
     */
    public void display(JTextArea chatArea) {
        chatArea.append("[E]");
        super.display(chatArea);
        chatArea.append(" (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")\n");
    }
}

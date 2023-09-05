package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTextArea;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a deadline task with description and due date.
     *
     * @param description Description of the deadline.
     * @param by Due date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the due date of the deadline.
     *
     * @return Due date.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Converts the deadline task into a string and display it.
     *
     * @param chatArea JTextArea where the message will be displayed.
     */
    public void display(JTextArea chatArea) {
        chatArea.append("[D]");
        super.display(chatArea);
        chatArea.append(" (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")\n");
    }
}

package chatbot.alain;

import java.time.LocalDate;

/**
 * Represents a task with a specific deadline.
 */
public class Deadlines extends Task {

    /**
     * The deadline time associated with the task.
     */
    protected String byTimeString;
    protected LocalDate byTime;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the task.
     * @param by The deadline time of the task.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.byTimeString = ChatbotAlain.stringToTimeString(this, by, true);
        if (byTime == null) {
            byTime = LocalDate.MAX;
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTimeString + ")";
    }
    @Override
    public void setTime(LocalDate date, boolean by) {
        this.byTime = date;
    }
    @Override
    public LocalDate getDate() {
        return this.byTime;
    }
}

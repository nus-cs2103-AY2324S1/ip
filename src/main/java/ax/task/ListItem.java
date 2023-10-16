package ax.task;

import java.time.LocalDate;

/**
 * Represents a single item in a to-do list.
 */
public class ListItem implements Reminders {

    @SuppressWarnings("FieldMayBeFinal")
    private String text;
    private boolean isDone;

    /**
     * Creates a new ax.task.ListItem with the given text.
     * The item is initially marked as not done.
     *
     * @param text the text of the item
     */
    public ListItem(String text) {
        this.text = text;
        this.isDone = false; // defaults to not done yet
        assert this.text.length() > 0 : this.isDone == false;
    }

    /**
     * Gets the text of the item.
     *
     * @return the text of the item
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the done status of the item.
     *
     * @return true if the item is done, false otherwise
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Toggles the done status of the item.
     * If the item was previously done, it is marked as not done.
     * If the item was previously not done, it is marked as done.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Sets the done status of the item.
     *
     * @param done true if the item is done, false otherwise
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Parses the date
     * @param date the string of date
     * @return a LocalDate Object
     */
    public LocalDate parseDate(String date) {
        return LocalDate.parse(date.strip());
    }

    /**
     * Returns a string representation of the item.
     * If the item is done, the string includes a tickbox.
     * If the item is not done, the string includes an empty box.
     *
     * @return a string representation of the item
     */
    @Override
    public String toString() {
        if (isDone) {
            // if done use tickbox
            return "[M] " + this.text;
        } else {
            return "[ ] " + this.text;
        }
    }

    /**
     * placeholder method to work woth inheritance
     * @return false
     */
    public boolean isDue() {
        return false;
    }

    /**
     * placeholder method to work woth inheritance
     * @return false
     */
    public LocalDate getDueDate() {
        return null;
    }
}

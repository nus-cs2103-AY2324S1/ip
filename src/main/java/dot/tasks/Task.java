package dot.tasks;

import java.time.LocalDateTime;

import dot.ui.Ui;

/**
 * This is the abstract class for Task, for which
 * Todo, Deadline and Event inherits from.
 */
public abstract class Task {

    private final String description;

    private boolean isCompleted;

    /**
     * This is the super constructor for Task, which all its
     * subclasses will call.
     *
     * @param description This is the description for the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * This is the overloaded constructor for Task.
     *
     * @param description This is the description for the Task.
     * @param isCompleted   This is the boolean representing the completeness of the Task.
     */
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    // Abstract methods

    /**
     * Checks whether the Task falls on the date which has a
     * start of day startOfDay and end of day endOfDay.
     *
     * @param startOfDay LocalDateTime representing the start of the day.
     * @param endOfDay   LocalDateTime representing the end of the day.
     * @return true if Task falls on the given date, else false.
     */
    public abstract boolean isOnDate(LocalDateTime startOfDay, LocalDateTime endOfDay);

    /**
     * Returns the format of the Task which will be
     * stored to the data file.
     *
     * @return Task as storage string.
     */
    public String getFileFormat() {
        return String.format("%d | %s", this.isCompleted ? 1 : 0, this.description);
    }

    /**
     * Toggles the done status of the Task.
     *
     * @param isCompleted This is the status of the Task to change to.
     */
    public void setComplete(boolean isCompleted) {
        if (this.isCompleted == isCompleted) {
            // Already marked / unmarked
            Ui.wrapPrintWithHorizontalRules(this.isCompleted
                    ? "Already marked done."
                    : "Already unmarked.");
        } else {
            this.isCompleted = isCompleted;
            Ui.displayMarkOrUnmark(this.isCompleted, this.toString());
        }
    }

    //Solution below inspired by
    //https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
    //Only refers to the getStatus method
    public char getStatus() {
        return this.isCompleted ? 'X' : ' ';
    }

    public String toString() {
        return String.format("[%c] %s", this.getStatus(), this.description);
    }

}

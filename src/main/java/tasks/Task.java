package tasks;

import ui.Ui;

import java.time.LocalDateTime;

/**
 * This is the abstract class for Task, for which
 * Todo, Deadline and Event inherits from.
 */
public abstract class Task {

    private String description;
    private boolean completed;

    /**
     * This is the super constructor for Task, which all its
     * subclasses will call.
     * @param description This is the description for the Task.
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * This is the overloaded constructor for Task.
     * @param description This is the description for the Task
     * @param completed This is the boolean representing the completeness of the Task
     */
    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    // Abstract methods

    /**
     * This checks whether the Task falls on the date which has a
     * start of day startOfDay and end of day endOfDay.
     * @param startOfDay LocalDateTime representing the start of the day
     * @param endOfDay LocalDateTime representing the end of the day
     * @return true if Task falls on the given date, else false
     */
    public abstract boolean isOnDate(LocalDateTime startOfDay, LocalDateTime endOfDay);

    /**
     * This method returns the format of the Task which will be
     * stored to the data file.
     * @return Task as storage string
     */
    public String getFileFormat() {
        return String.format("%d | %s", this.completed ? 1 : 0, this.description);
    };

    /**
     * This method toggles the done status of the Task.
     * @param newStatus This is the status of the Task to change to
     */
    public void toggleStatus(boolean newStatus) {
        if (this.completed == newStatus) {
            // Already marked / unmarked
            Ui.wrapPrintWithHorizontalRules(this.completed
                                            ? "Already marked done."
                                            : "Already unmarked.");
        } else {
            this.completed = newStatus;
            Ui.displayMarkOrUnmark(this.completed, this.toString());
        }
    }

    //Solution below inspired by
    //https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
    //Only refers to the getStatus method
    protected char getStatus() {
        return this.completed ? 'X' : ' ';
    }

    public String toString() {
        return String.format("[%c] %s", this.getStatus(), this.description);
    }
}

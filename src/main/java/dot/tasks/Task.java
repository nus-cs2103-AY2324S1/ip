package dot.tasks;

import dot.ui.Ui;

import java.time.LocalDateTime;

public abstract class Task {

    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    // Abstract methods
    public abstract boolean isOnDate(LocalDateTime startOfDay, LocalDateTime endOfDay);

    public String getFileFormat() {
        return String.format("%d | %s", this.isCompleted ? 1 : 0, this.description);
    };

    public void toggleStatus(boolean newStatus) {
        if (this.isCompleted == newStatus) {
            // Already marked / unmarked
            Ui.wrapPrintWithHorizontalRules(this.isCompleted
                                            ? "Already marked done."
                                            : "Already unmarked.");
        } else {
            this.isCompleted = newStatus;
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

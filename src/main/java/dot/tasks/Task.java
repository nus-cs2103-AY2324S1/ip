package dot.tasks;

import dot.ui.Ui;

import java.time.LocalDateTime;

public abstract class Task {

    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    // Abstract methods
    public abstract boolean isOnDate(LocalDateTime startOfDay, LocalDateTime endOfDay);

    public String getFileFormat() {
        return String.format("%d | %s", this.completed ? 1 : 0, this.description);
    };

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
    public char getStatus() {
        return this.completed ? 'X' : ' ';
    }

    public String toString() {
        return String.format("[%c] %s", this.getStatus(), this.description);
    }
}

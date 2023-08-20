package tasks;

import ui.Ui;

public class Task {

    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
        return this.completed ? 'X': ' ';
    }
    public String toString() {
        return String.format("[%c] %s", this.getStatus(), this.description);
    }
}

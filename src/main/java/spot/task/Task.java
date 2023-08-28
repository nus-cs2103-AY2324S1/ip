package spot.task;

import java.time.LocalDate;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task() {

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Wow! Spot has marked this task as done!");
        System.out.println("  " + this);
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("Spot will mark this task as not done yet then!");
        System.out.println("  " + this);
    }

    public String getStatusIcon() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toLine() {
        return this.getStatusIcon() + " | " + this.description;
    }

    public abstract boolean fallsOn(LocalDate date);

    public boolean descriptionContains(String keyword) {
        return description.contains(keyword);
    }
}

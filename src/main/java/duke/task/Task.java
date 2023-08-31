package duke.task;

public class Task {
    private String task;
    private boolean done = false;

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public String toString() {
        String message;
        if (this.done) {
            message = "[X] " + this.task;
        } else {
            message = "[ ] " + this.task;
        }
        return message;
    }

    public void markTask() {
        this.done = true;
    }

    public void unmarkTask() {
        this.done = false;
    }

    public String getTaskFileString() {
        return (done ? "1" : "0") + " , " + this.task;
    }

    public void printStart() {
        System.out.println("    This task has no start time");
    }

    public void printEnd() {
        System.out.println("    This task has no end time");
    }

    public void printDueDate() {
        System.out.println("    This task has no due date");
    }
}

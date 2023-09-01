package duke.task;

public class Task {
    private String task;
    private boolean done;

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

    public boolean markTask() {
        this.done = true;
        return this.done;
    }

    public boolean unmarkTask() {
        this.done = false;
        return this.done;
    }

    public String getTaskFileString() {
        return (done ? "1" : "0") + " , " + this.task;
    }

    /**
     * Check if task description contains the keyword.
     *
     * @return true if the task description contains the keyword.
     */
    public boolean contains(String keyword) {
        return this.task.contains(keyword);
    }
}

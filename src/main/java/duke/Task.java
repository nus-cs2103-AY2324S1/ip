package duke;

public class Task {

    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task() {
        this.task = " ";
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String getStatus() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getTaskType(Task task) {
        if (task instanceof Event) {
            return "E | " + this.task + "| " + ((Event) task).getStartDateTime() + " to " + ((Event) task).getEndDateTime();
        } else if (task instanceof Deadline) {
            return "D | " + this.task + "| " + ((Deadline) task).getTime();
        } else if (task instanceof ToDo) {
            return "T | " + this.task;
        } else {
            return "Unknown";
        }
    }

    @Override
    public String toString() {
        return "[" + (this.isDone? "X" : " ") + "] " + this.task;
    }
}
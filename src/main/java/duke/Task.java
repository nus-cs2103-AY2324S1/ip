package duke;

import java.time.format.DateTimeFormatter;

public class Task {
    // inspired by partial solution on website
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public boolean isDone() { return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String taskToFileString() {
        String taskType = "";
        String doneStatus = this.isDone() ? "1" : "0";
        String taskDescription = this.getDescription();
        String start = "";
        String end = "";

        if (this instanceof ToDo) {
            taskType = "T";
        } else if (this instanceof Deadline) {
            taskType = "D";
            end = ((Deadline) this).date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (this instanceof Event) {
            taskType = "E";
            start = ((Event) this).start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            end = ((Event) this).end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return taskType + " || " + doneStatus + " || " + taskDescription + " || " + start + " || " + end;
    }
}

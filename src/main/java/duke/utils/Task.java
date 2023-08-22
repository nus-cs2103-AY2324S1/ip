package duke.utils;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task parseInputAsTask(String input) {
        if (input.startsWith("event")) {
            return new Event(input.substring(6));

        } else if (input.startsWith("todo")){
            return new ToDo(input.substring(5));

        } else if (input.startsWith("deadline")){
            return new Deadline(input.substring(9));
        } else {
            return null;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

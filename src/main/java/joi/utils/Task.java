package joi.utils;

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

    public static Task parseInputAsTask(String input) throws InvalidCommandException {
        if (input.startsWith("event")) {
            return new Event(input);

        } else if (input.startsWith("todo")){
            return new ToDo(input);

        } else if (input.startsWith("deadline")){
            return new Deadline(input);

        } else {
            throw new InvalidCommandException(input);
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

    public String getEventType() {
        return "task";
    }

    public String getDescription() {
        return this.description;
    }

    public String getSaveString() {
        return getEventType() + "#" + getDescription() + "#" + (isDone ? "X" : "N");
    }
}

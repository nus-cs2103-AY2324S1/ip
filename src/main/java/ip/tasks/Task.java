package ip.tasks;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; // Initially, the task is not done
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String toFileFormat() {
        return (getIsDone() ? "1" : "0") + " | " + getDescription();
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                return Todo.fromFileFormat(line);
            case "D":
                return Deadline.fromFileFormat(line);
            case "E":
                return Event.fromFileFormat(line);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}


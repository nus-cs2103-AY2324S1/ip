import java.util.Arrays;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");    // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public abstract String writeTaskToFile();

    public static Task readTaskFromFile(String input) {
        String[] args = input.split(" \\| ");
        Task task;
        switch (args[0]) {
        case "T":
            task = Todo.readTaskFromFile(args);
            break;
        case "D":
            task = Deadline.readTaskFromFile(args);
            break;
        case "E":
            task = Event.readTaskFromFile(args);
            break;
        default:
            task = null;
            break;
        }
        return task;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}

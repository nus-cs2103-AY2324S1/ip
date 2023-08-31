package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String toSaveString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public static Task parseSavedTask(String savedString) {
        String[] parts = savedString.split(" \\| ");
        if (parts.length == 2) {
            String status = parts[0];
            String description = parts[1];
            Task task = new Task(description);
            if (status.equals("1")) {
                task.markAsDone();
            }
            return task;
        }
        return null;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}

import java.util.Arrays;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String status() {
        String done = isDone ? "1" : "0";
        return done + "|" + description;
    }

    public abstract String save();

    public static Task fromString(String taskString) {
        String[] parts = taskString.split("\\|");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                Task task = new ToDo(description);
                if (isDone) {
                  task.markAsDone();
                }
                return task;
            case "D":
                String by = parts[3]; // Extract deadline
                Task deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                String from = parts[3]; // Extract start date
                String to = parts[4]; // Extract end date
                Task event = new Events(description, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }
}

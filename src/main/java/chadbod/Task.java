package chadbod;

import java.time.LocalDateTime;

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

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public static Task fromString(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3 && parts[0].equals("T")) {
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task t = new Todo(description);
            if (isDone) {
                t.markDone();
            }
            return t;
        } else if (parts.length >= 4 && parts[0].equals("D")) {
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDateTime deadline = LocalDateTime.parse(parts[3]);
            Task t = new Deadline(description, deadline);
            if (isDone) {
                t.markDone();
            }
            return t;
        } else if (parts.length >= 5 && parts[0].equals("E")) {
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            LocalDateTime from = LocalDateTime.parse(parts[3]);
            LocalDateTime to = LocalDateTime.parse(parts[4]);
            Task t = new Event(description, from, to);
            if (isDone) {
                t.markDone();
            }
            return t;
        } else {
            return null;
        }
    }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    public abstract String toFileString();
}

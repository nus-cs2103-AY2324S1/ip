public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toSaveString() {
        return String.format("%s|%s", isDone ? "1" : "0", description);
    }

    public static Task parseTask(String line) {
        String[] split = line.split("\\|");
        String type = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];

        switch (type) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                String by = split[3];
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                String start = split[3];
                String end = split[4];
                Event event = new Event(description, start, end);
                if (isDone) event.markAsDone();
                return event;
            default:
                return null;
        }
    }
}

package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    public String toFormattedString() {
        return "";
    }

    public static Task createTaskFromFormattedString(String formattedString) {
        String[] parts = formattedString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        if (taskType.equals("T")) {
            Task todo =  new ToDo(description);
            todo.isDone = isDone;
            return todo;
        } else if (taskType.equals("D")) {
            String by = parts[3];
            Task deadline =  new Deadline(description, by);
            deadline.isDone = isDone;
            return deadline;
        } else if (taskType.equals("E")) {
            String from = parts[3];
            String to = parts[4];
            Task event = new Event(description, from, to);
            event.isDone = isDone;
            return event;
        } else {
            // Handle unrecognized task type
            return null;
        }
    }
}
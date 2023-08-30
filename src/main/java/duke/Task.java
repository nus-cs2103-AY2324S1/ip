package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String saveTask() {
        if (this.isDone) {
            return "1" + " | " + this.description;
        } else {
            return "0" + " | " + this.description;
        }
    }

    public static Task loadData(String savedTasks) {
        String[] parts = savedTasks.split(" \\| ");
        String type = parts[0];
        String doneOrNot = parts[1];
        String description = parts[2];

        Task task;
        if (doneOrNot.equals("1")) {
            if (type.equals("T")) {
                task = new Todo(description);
                task.markAsDone();
            } else if (type.equals("D")) {
                String by = parts[3];
                task = new Deadline(description, by);
                task.markAsDone();
            } else {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                task.markAsDone();
            }
        } else {
            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D")) {
                String by = parts[3];
                task = new Deadline(description, by);
            } else {
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
            }
        }
            return task;
    }


    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }


}
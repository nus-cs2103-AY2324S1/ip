package urchatbot.tasks;

/**
 * Represents a task with its essential properties.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Construct the Task class.
     *
     * @param description Description of the task.
     * @param isDone If the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }
    /**
     * Unmarks the task as done.
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Converts the tasks into a format to store.
     */
    public String toFileString() {
        return "";
    };
    /**
     * Converts tasks in the stored tasklist to Task subclass.
     *
     * @param input Task in the stored tasklist.
     */
    public static Task fromString(String input) {
        String[] parts = input.split("\\|");
        String taskType = parts[0].trim();
        boolean isMarked = parts[1].trim().equals("1");
        String taskDescription = parts[2].trim();

        if (taskType.equals("T")) {
            return new ToDo(taskDescription, isMarked);
        } else if (taskType.equals("D")) {
            return new Deadline(taskDescription, isMarked, parts[3].trim());
        } else if (taskType.equals("E")) {
            String[] duration = parts[3].trim().split("-");
            String from = duration[0].trim();
            String to = duration[1].trim();
            return new Event(taskDescription, isMarked, from, to);
        } else {
            return null;
        }
    }
    /**
     * Marks down task with X
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public boolean getIsDone() {
        return this.isDone;
    }
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    public String getDescription() {
        return this.description;
    }
}


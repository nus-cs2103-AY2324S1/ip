package urchatbot.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUnDone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    public String toFileString(){
        return "";
    };

    // Define this static method in your urchatbot.tasks.Task class
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
}


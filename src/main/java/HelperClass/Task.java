package HelperClass;
public class Task {
    private boolean isDone;
    private String taskName;
    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;

    }

    public void markDone() {
        System.out.println("Nice! I've marked this task as done:");
        isDone = true;
        System.out.println("[X] " + taskName);
    }

    public void unmarkDone() {
        System.out.println("OK, I've marked this task as not done yet:");
        isDone = false;
        System.out.println("[ ] " + taskName);
    }

    public String display() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}

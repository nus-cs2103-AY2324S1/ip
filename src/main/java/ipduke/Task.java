package ipduke;

public class Task {
    private String task;
    private boolean done = false;

    Task(String task) {
        this.task = task;
    }

    public String toString() {
        String message;
        if (this.done) {
            message = "[X] " + this.task;
        } else {
            message = "[ ] " + this.task;
        }
        return message;
    }

    public void markTask() {
        this.done = true;
        System.out.println("chirp chirp! I've marked this task as done:\n" + this.toString());
    }

    public void unmarkTask() {
        this.done = false;
        System.out.println("chirp! I've mark this task as not done yet:\n" + this.toString());
    }
}

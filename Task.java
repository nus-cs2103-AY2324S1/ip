import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected int id;
    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  [X] " + description);
    }

    public void markAsUndone() {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  [ ] " + description);
    }

    @Override
    public String toString() {
        String status = ". [" + getStatusIcon() + "] ";
        return String.valueOf(this.id) + status + this.description;
    }

    public void addTask(String taskDescription) {
        System.out.println("Got it. I've added this task:");
        System.out.println(this.toString().substring(2));
    }
}

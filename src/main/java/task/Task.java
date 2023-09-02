package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        if (isDone) {
            System.out.println("Task is already done! (≧∇≦)/");
        } else {
            this.isDone = true;
        }
    }

    public void markUndone() {
        if (isDone) {
            this.isDone = false;
        } else {
            System.out.println("Task is not done yet! (;° ロ°)");
        }
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}

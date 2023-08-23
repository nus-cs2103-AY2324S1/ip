public class Task {
    private final String taskName;
    private boolean isComplete = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void markComplete() {
        this.isComplete = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void markIncomplete() {
        this.isComplete = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    private String getStatus() {
        return isComplete ? "[X] " : "[ ] ";
    }

    @Override
    public String toString() {
        return getStatus() + this.taskName;
    }
}

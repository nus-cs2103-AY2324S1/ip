public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markTaskDone() {
        this.isDone = true;
        System.out.println("\t  " + this);
    }

    public void markTaskNotDone() {
        this.isDone = false;
        System.out.println("\t  " + this);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}

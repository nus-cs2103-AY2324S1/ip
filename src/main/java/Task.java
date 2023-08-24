public class Task {

    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.taskName;
    }

    private void markPrintHelper(String message) {
        String filler = "____________________________________________________________";
        System.out.println(filler);
        System.out.println(message);
        System.out.println(this);
        System.out.println(filler);
    }

    public void markAsDone() {
        this.isDone = true;
        markPrintHelper(" Nice! I've marked this task as done:");
    }

    public void unMark() {
        this.isDone = false;
        markPrintHelper(" OK, I've marked this task as not done yet:");
    }

}

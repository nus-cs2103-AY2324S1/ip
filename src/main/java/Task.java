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

    public void markAsDone() {
        this.isDone = true;
        String filler = "____________________________________________________________";
        System.out.println(filler);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("[" + getStatusIcon() + "] " + this.taskName);
        System.out.println(filler);
    }

    public void unMark() {
        this.isDone = false;
        String filler = "____________________________________________________________";
        System.out.println(filler);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("[" + getStatusIcon() + "] " + this.taskName);
        System.out.println(filler);
    }

}

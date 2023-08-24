public class Task {
    private String task;
    private boolean isDone;

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return this.task;
    }

    public String getStatus() {
        String status = isDone ? " " : "X";
        return "[" + status + "]" + " " + task;
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(getStatus());
    }

    public void unmarkDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(getStatus());
    }
}

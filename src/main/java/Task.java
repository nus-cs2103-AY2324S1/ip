public class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
        String msg = "Nice! I've marked this task as done:\n"
                + "\t[X] " + this.description + "\n";
        System.out.println(msg);
    }

    public void markAsUndone() {
        this.isDone = false;
        String msg = "OK, I've marked this task as not done yet:\n"
                + "\t[ ] " + this.description + "\n";
        System.out.println(msg);
    }

    @Override
    public String toString() {
        String msg = "[" + (isDone ? "X" : " ") + "]";
        msg = msg + " " + this.description;
        return msg;
    }
}

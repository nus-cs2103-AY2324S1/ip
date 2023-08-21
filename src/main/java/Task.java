public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
        MessagePrint.print("Nice! I've marked this task as done:\n"
                + "[X]"
                + this.description
        );
    }

    public void unmark() {
        this.isDone = false;
        MessagePrint.print(
                "OK, I've marked this task as not done yet:\n"
                        +  "[ ]"
                        + this.description
        );
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

}

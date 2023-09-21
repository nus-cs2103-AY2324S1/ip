package duke;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task.
     * @param description  Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     * @param flag Flag to indicate whether to print out the message.
     * @return The message to be printed.
     */
    public String mark(boolean flag) {
        //flag checks if the message is coming from storage class (loading does not need to print)
        if (!flag) {
            this.isDone = true;
        } else {
            System.out.println("Nice! I've marked this task as done:\n");
            this.isDone = true;
            System.out.println(this);
            return "Nice! I've marked this task as done:\n" + this;
        }
        return "";
    }

    public String unmark() {
        System.out.println("OK, I've marked this task as not done yet:\n");
        this.isDone = false;
        System.out.println(this);
        return "OK, I've marked this task as not done yet:\n" + this;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

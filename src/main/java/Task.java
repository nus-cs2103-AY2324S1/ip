/**
 * Task encapsulate a task with a String description and boolean isDone. It supports
 * marking the completion of tasks
 *
 * @author: Low Jun Yu
 * @version: CS2103T AY23/24 Semester 1
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a Task with a given description. Completion of the task
     * is false by default
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of task with "X" meaning task is completed
     * @return "X" if completed " " if still in progress
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a formatted string of the status of the task
     * @return String containing completion status and description of task
     */
    public String toString() {
        return " [" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the current task as completed
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println(Printer.line);
        System.out.println("Nice! I've Marked this task as done:");
        System.out.println(this);
        System.out.println(Printer.line);
    }

    /**
     * Unmarks the current task as not completed
     */
    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println(Printer.line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
        System.out.println(Printer.line);
    }

}

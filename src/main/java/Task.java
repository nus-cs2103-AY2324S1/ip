/**
 * Task represents a task given by a user's input
 * It contains details of a task
 */

public class Task {

    private String taskName;
    private boolean isDone;

    /**
     * Constructor for creating a task
     * @param taskName name of task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Completes a task when called by setting it as done
     */
    public void completeTask() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        // Double space to maintain consistency with rubrics
        System.out.println("  " + this.toString());
    }

    /**
     * Mark a task as undone by setting done as false
     */
    public void undoTask() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        // Double space to maintain consistency with rubrics
        System.out.println("  " + this.toString());
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}

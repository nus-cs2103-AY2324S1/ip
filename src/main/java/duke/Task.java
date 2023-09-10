package duke;
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
     * Completes a task without printing anything
     */
    public void quietlyCompleteTask() {
        this.isDone = true;
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

    /**
     * Checks whether a task is completed and return an X if done
     * @return String X if done, a blank space string if not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks whether a task is done through a string
     * @return  a string containing whether the task is done
     */
    public String isDone() {
        return String.valueOf(this.isDone);
    }

    /**
     * Prints out a message that a task has been added
     */
    public void taskAdded(int noOfTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + noOfTask + " tasks in the list.");
    }
    /**
     * Prints out a message that a task has been deleted
     */
    public void taskDeleted(int noOfTask) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + noOfTask + " tasks in the list.");
    }

    /**
     * Converts the task to a format that can be saved
     * @return  a string that can be saved in the storage in a particular format
     */
    public String convertToSaveFormat() {
        return "";
    }

    /**
     * Returns the task name
     * @return  a string containing the task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[" + this.getStatusIcon() + "] " + this.taskName;
        } else {
            return "[" + this.getStatusIcon() + "] " + this.taskName;
        }
    }

}

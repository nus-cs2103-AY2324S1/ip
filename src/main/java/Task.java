/**
 * Abstract class representing a general task.
 * Specific types of tasks (e.g., ToDo, Deadline, Event) should extend this class.
 */
public abstract class Task {
    protected String description;  // Description of the task
    protected boolean isDone; // Task completion status
    protected String type; // Type of the task (e.g., TODO, DEADLINE, EVENT)
    protected static int taskCount = 0; // Counter for the number of tasks created

    /**
     * Abstract method to get additional details of a task.
     * Implementation should be provided in subclasses.
     *
     * @return A string representing additional details of the task.
     */
    public abstract String getDetails();

    public abstract String getDBString();


    /**
     * Constructor to initialize a Task object.
     *
     * @param description Description of the task.
     * @param type        Type of the task.
     */
    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        Task.taskCount += 1;
    }

    public static void clear() {
        Task.taskCount = 0;
    }

    /**
     * Prints the description of the added task and the current task count.
     */
    public void addedTaskDescription() {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + this.getStatusAndDescription());
        System.out.println(String.format("Now you have %s tasks in the list.",Task.taskCount));
    }

    /**
     * Returns the status icon based on task completion.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the task type.
     */
    public String getType() {
        return this.type;

    }

    /**
     * Returns the status icon, task type, description, and additional details.
     *
     * @return A formatted string representing the task.
     */
    public String getStatusAndDescription() {
        return String.format("[%s][%s] %s%s",
                this.getType(),
                this.getStatusIcon(),
                this.description,
                this.getDetails());
    }

    /**
     * Marks the task as done and prints a confirmation message.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.getStatusAndDescription());
    }

    /**
     * Marks the task as not done and prints a confirmation message.
     */
    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + this.getStatusAndDescription());
    }

    /**
     * Deletes the task, decrements the task count, and prints a confirmation message.
     */
    public void deleteTask() {
        taskCount -= 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + this.getStatusAndDescription());
        System.out.println(String.format("Now you have %s tasks in the list.",Task.taskCount));

    }

    public boolean isDone() {
        return this.isDone;
    }

    public String String() {
        return this.description;
    }
}

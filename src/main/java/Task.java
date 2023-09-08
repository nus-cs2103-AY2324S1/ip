/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * 
     * @param description Description of the task.
     * @param isDone      Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     * 
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description; // getter
    }

    /**
     * Returns the isDone status of the task.
     * 
     * @return Whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Returns the status and description of the task, to output to the user.
     * 
     * @return A String, properly formatting the status and description of the task.
     */
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    /**
     * Toggle the isDone status of the task.
     */
    public void toggle() {
        this.isDone = !this.isDone;
    }

    /**
     * Analyse line of text in tasks.txt and returns the corresponding task.
     * 
     * @param taskLine  Line of text in tasks.txt
     * @return The corresponding task
     */
    public static Task fromString(String taskLine) {
        if (taskLine.charAt(0) == 'D') {
            String[] parts = taskLine.split("\\|"); // Split the string by the | character

            String taskDescription = parts[2].trim(); // Extract and trim the task description
            String deadline = parts[3].trim(); // Extract and trim the deadline
            int temp = Integer.parseInt(parts[1].trim()); // Extract the isDone status
            boolean isDone = (temp == 1); // Set as boolean

            Task newDeadline = new Deadline(taskDescription, isDone, deadline);
            return newDeadline;
        } else if (taskLine.charAt(0) == 'E') {
            String[] parts = taskLine.split("\\|"); // Split the string by the | character

            String taskDescription = parts[2].trim(); // Extract and trim the task description
            String from = parts[3].trim(); // Extract and trim the "from" time
            String to = parts[3].trim(); // Extract and trim the "to" time
            int temp = Integer.parseInt(parts[1].trim()); // Extract the isDone status
            boolean isDone = (temp == 1); // Set as boolean

            Task newEvent = new Event(taskDescription, isDone, from, to);
            return newEvent;
        } else if (taskLine.charAt(0) == 'T') {
            String[] parts = taskLine.split("\\|"); // Split the string by the | character

            String taskDescription = parts[2].trim(); // Extract and trim the task description
            int temp = Integer.parseInt(parts[1].trim()); // Extract the isDone status
            boolean isDone = (temp == 1); // Set as boolean

            Task newTodo = new Todo(taskDescription, isDone);
            return newTodo; 
        } else {
            return null;
        }
    }

}

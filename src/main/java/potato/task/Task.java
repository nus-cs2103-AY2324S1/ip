package potato.task;

/**
 * The Task class represents a general task with a description, completion status, and priority.
 * It serves as the base class for specific task types like Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String priority;

    /**
     * Constructs a Task object with the provided description, completion status, and priority.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param priority    The priority of the task.
     */
    public Task(String description, boolean isDone, String priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Parses user input to create a specific task type based on the input format.
     *
     * @param input The user input string representing the task.
     * @return A specific task object created from the input or null if the input is not recognized.
     */
    public static Task parse(String input) {
        if (input.startsWith("todo ")) {
            return Todo.parseTodo(input.substring(5), "0");

        } else if (input.startsWith("deadline ")) {
            // empty deadline
            // no by
            return Deadline.parseDeadline(input.substring(9));

        } else if (input.startsWith("event ")) {
            // empty event
            // no from
            // no to
            return Event.parseEvent(input.substring(6));
        } else {
            return null;
        }
    }

    /**
     * Parses a saved representation of a task and creates an appropriate task object based on its type.
     *
     * @param input The string representation of a task saved in a specific format.
     * @return A task object created from the saved data or null if the input is invalid.
     */
    public static Task parseSaved(String input) {
        String[] parts = input.split(" \\| ");
        System.out.println(parts[0]);
        System.out.println(parts[1]);
        System.out.println(parts[2]);

        switch (parts[0]) {
        case "T":
            System.out.println("its todo");
            return Todo.parseSavedTodo(parts);
        case "D":
            // empty deadline
            // no by
            return Deadline.parseSavedDeadline(parts);
        case "E":
            // empty event
            // no from
            // no to
            return Event.parseSavedEvent(parts);
        default:
            return null;
        }
    }

    /**
     * Gets the completion status of the task.
     *
     * @return "X" if the task is completed, or " " if it is not completed.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the completion status of the task.
     *
     * @param bool The new completion status of the task.
     */
    public void setStatus(boolean bool) {
        isDone = bool;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority The new priority of the task.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Converts the task object to a string representation suitable for saving.
     *
     * @return A string representation of the task for saving.
     */
    public String toSave() {
        return "";
    }

    /**
     * Converts the task object to a string for displaying to the user.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + getStatus() + "]" + "[" + priority.toUpperCase() + "] " + description;
    }
}

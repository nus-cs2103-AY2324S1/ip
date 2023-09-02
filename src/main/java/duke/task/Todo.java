package duke.task;

/**
 * The Todo class represents a task with a description
 * , which is a subclass of the `Task` class.
 */
public class Todo extends Task {
    /**
     * Constructs a new `Todo` task with the given description.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task for display to the user.
     * @return A formatted string containing the task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task to a string representation for saving to a file.
     * @return A formatted string representing the task type and description.
     */
    @Override
    public String toFileString() {
        char taskType = 'T';
        return taskType + " | " + super.toFileString();
    }

    /**
     * Creates a `Todo` task object from a data string. Used for deserialization.
     * @param taskData The data string containing todo task information.
     * @return A `Todo` task object created from the data string, or `null` if the data is incomplete or invalid.
     */
    public static Todo createTodoFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 3 && taskParts[0].trim().equals("T")) {
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();

            Todo todo = new Todo(description);
            if (doneStatus.equals("1")) {
                todo.markDone();
            }
            return todo;
        }

        return null; // Incomplete data
    }

}

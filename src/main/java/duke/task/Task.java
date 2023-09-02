package duke.task;

/**
 * The `Task` class represents a basic task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with the given description, initially marked as not done.
     * @param description The description of the task.
     */

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the type of the task. Subclasses should override this method.
     * @return An empty string for the base class.
     */
    public String getTaskType() {
        return ""; // Return an empty string for the base class
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     * @return `true` if the task is done, otherwise `false`.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task's completion status.
     * @return "X" if the task is done, or a space character if it's not done.
     */
    public String taskStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Converts the task to a string representation for saving to a file.
     * @return A formatted string representing the task's data.
     */
    public String toFileString() {
        char doneStatus = isDone ? '1' : '0';
        return doneStatus + " | " + description;
    }

    /**
     * Converts the task to a string representation for displaying to the user.
     * @return A formatted string representing the task's type, status, and description.
     */
    @Override
    public String toString() {
        return getTaskType() + " [" + taskStatus() + "] " + description;
    }

    /**
     * Creates a task object from a data string
     * @param taskData The data string containing task information.
     * @return A task object created from the data string, or `null` if the data is incomplete or invalid.
     */
    public static Task createTaskFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 3) {
            String taskType = taskParts[0].trim();
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();

            Task task;

            if (taskType.equals("T")) {
                task = Todo.createTodoFromData(taskData);
            } else if (taskType.equals("D")) {
                task = Deadline.createDeadlineFromData(taskData);
            } else if (taskType.equals("E")) {
                task = Event.createEventFromData(taskData);
            } else {
                System.out.println("Unrecognized task type: " + taskType);
                return null;
            }

            if (doneStatus.equals("1")) {
                task.markDone();
            }
            return task;
        }
        return null; // Incomplete data
    }
}

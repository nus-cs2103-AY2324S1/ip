package task;

/**
 * The enumeration of all possible task types: TODO, DEADLINE, and EVENT.
 */
enum TaskType {
    TODO, DEADLINE, EVENT
}

/**
 * Represents an abstract Task class with a type, name, and completion status.
 * The Task class serves as a template for different types of tasks such as TODO, DEADLINE, and EVENT.
 */
public abstract class Task {
    private String taskName;
    private boolean isDone = false;
    private TaskType taskType;

    /**
     * Constructs a Task object with a specified name and type of task.
     *
     * @param taskName The name or description of the task.
     * @param taskType The type of the task, as defined in the TaskType enum.
     */
    public Task(String taskName, TaskType taskType) {
        this.taskName = taskName;
        this.taskType = taskType;
    }


    /**
     * Returns a string representation of the task, indicating its completion status and name.
     *
     * @return A string representing the task, where a done task is represented with "[X]"
     *         and an undone task with "[ ]", followed by the task name.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    /**
     * Marks the task as done by setting the isDone attribute to true.
     */

    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting the isDone attribute to false.
     */
    public void unmarkDone() {
        this.isDone = false;
    }
}

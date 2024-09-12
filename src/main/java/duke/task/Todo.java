package duke.task;

/**
 * Represents a "to-do" type of task.
 */
public class Todo extends Task {

    /**
     * Initializes a new "to-do" task with the specified description.
     *
     * @param description The description of the "to-do" task.
     */
    public Todo(String description) {

        super(description, TaskType.TODO);
    }

    /**
     * Initializes a new "to-do" task with the specified name and completion status.
     *
     * @param name The name of the "to-do" task.
     * @param isDone The completion status of the task.
     */
    public Todo(String name, boolean isDone) {

        super(name, TaskType.TODO, isDone);
    }


    /**
     * Returns the string representation of the "to-do" task.
     *
     * @return A string representing the "to-do" task's type, status, and description.
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}

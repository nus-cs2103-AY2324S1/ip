package duke;

/**
 * Represents a Todo task, which is a type of task with no specific deadline.
 * It inherits from the Task class.
 */
public class Todo extends Task{

    /**
     * Constructs a Todo object with the provided task description.
     *
     * @param desc The description of the Todo task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Returns a string representation of the Todo task in a format suitable for saving to a file.
     *
     * @return A formatted string representing the Todo task's details for saving to file.
     */
    @Override
    public String saveString() {
        String completedString = isCompleted ? "X|" : " |";
        return "T|" + completedString + description;
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

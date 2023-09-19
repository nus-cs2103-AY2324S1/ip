package duke.task;

/**
 * Represents a "ToDo" task, extending the abstract Task class.
 * A "ToDo" task is a simple task with only a description and no time or date associated with it.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo instance with a specified task name,
     * automatically setting its task type to TODO.
     *
     * @param taskName The name or description of the ToDo task.
     */
    public ToDo(String taskName) {
        super(taskName, TaskType.TODO);
    }

    /**
     * Returns a string representation of the ToDo task,
     * which includes its task type represented as "[T]"
     * followed by its status (done or not done) and its name.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parses a string representation of a ToDo task back into a ToDo object.
     * It reads the task's description and completion status from the given string
     * and creates a corresponding ToDo object.
     *
     * @param line The string representation of the ToDo task, typically read from a data file.
     * @return A ToDo object that corresponds to the data in the given line of String.
     */
    public static ToDo parseFromString(String line) {
        int firstCloseBracketIndex = line.indexOf(']');
        String description = line.substring(firstCloseBracketIndex + 5);
        String marking = line.substring(firstCloseBracketIndex + 2, firstCloseBracketIndex + 3);
        ToDo task = new ToDo(description);

        // if task is initially marked done, then mark the task as done
        if (marking.equals("X")) {
            task.markDone();
        }

        return task;
    }
}

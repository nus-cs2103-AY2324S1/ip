import java.util.Objects;

/**
 * Task containing the description and own task state.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Create a task based on task type and input string.
     *
     * @param taskType The string representing the task type.
     * @param input    The input string used for creating a new task.
     * @return The created task.
     * @throws InsufficientArgumentsException If input is insufficient to create task.
     */
    public static Task createTask(String taskType, String input) throws InsufficientArgumentsException {
        if (Objects.equals(input, "")) {
            throw new InsufficientArgumentsException(String.format(
                    DukeConstants.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "description", taskType));
        }

        String description = input;
        String[] args;
        switch (taskType) {
            case "deadline":
                Task.validateContainsArgument(input, taskType, "by");
                args = input.split("/by");
                description = args[0].trim();
                String by = args[1].trim();
                return new Deadline(description, by);
            case "event":
                Task.validateContainsArgument(input, taskType, "from");
                Task.validateContainsArgument(input, taskType, "to");
                args = input.split("/from|/to");
                description = args[0].trim();
                String from = args[1].trim();
                String to = args[2].trim();
                return new Event(description, from, to);
            case "todo":
                return new ToDo(description);
            default:
                return null;
        }
    }

    /**
     * Get the appropriate status icon string for the task.
     *
     * @return The status icon for the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark a task.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Helper function to validate arguments in input string.
     *
     * @param input The input string used to create task.
     * @param parameterName The parameter name to be checked.
     * @throws InsufficientArgumentsException If input is missing arguments from task.
     */
    private static void validateContainsArgument(String input, String taskType, String parameterName)
            throws InsufficientArgumentsException {
        if (!input.contains(String.format("/%s", parameterName))) {
            throw new InsufficientArgumentsException(String.format(
                    DukeConstants.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, parameterName, taskType));
        }
    }
}
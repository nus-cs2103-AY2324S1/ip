package duck.task;

import duck.DuckException;

/**
 * Represents a todo task.
 */
public class TodoTask extends Task {

    /**
     * Creates a new TodoTask.
     * 
     * @param name   Name of the task.
     * @param isDone Status of the task.
     */
    public TodoTask(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String stringify() {
        return "T" + super.stringify();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parses the savable string representation of a TodoTask.
     * Implementation depends on stringify format.
     * 
     * @param fileLine Savable string representation of the task.
     * @return TodoTask parsed from the string representation.
     * @throws DuckException If the string representation is invalid.
     */
    public static TodoTask parse(String fileLine) throws DuckException {

        // Finding isDone
        boolean isDone = fileLine.charAt(1) == '1';

        // Finding name
        int slashIndex = fileLine.indexOf("/");
        String name = fileLine.substring(slashIndex + 1);

        return new TodoTask(name, isDone);
    }
}
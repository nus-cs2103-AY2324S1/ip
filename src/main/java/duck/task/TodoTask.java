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
    public TodoTask(String name, boolean isDone, String tag) {
        super(name, isDone, tag);
    }

    @Override
    public String stringify() {
        return "T" + super.stringify()
                + this.tag.length() + "/" + this.tag;
    }

    @Override
    public String toString() {
        String tagSuffixString = "";
        if (!this.tag.equals("")) {
            tagSuffixString = " #" + this.tag;
        }
        return "[T]" + super.toString() + tagSuffixString;
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
        int nameLength = Integer.parseInt(fileLine.substring(2, slashIndex));
        String name = fileLine.substring(slashIndex + 1, slashIndex + 1 + nameLength);

        // Finding tag
        int tagIndex = fileLine.indexOf("/", slashIndex + 1);
        String tag = fileLine.substring(tagIndex + 1);

        return new TodoTask(name, isDone, tag);
    }
}

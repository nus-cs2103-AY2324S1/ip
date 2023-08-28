package taskutil;

public class Todo extends Task {

    public Todo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Convert To-do task to a string for storing in data file.
     * @return Formatted string with data for To-do task.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}

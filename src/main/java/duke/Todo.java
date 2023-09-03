package duke;

/**
 * A subclass of the task.
 */

public class Todo extends Task {
    private String Input;
    public Todo(String name, String input) {
        super(name, input);
    }

    /**
     * A string representation of the todo task.
     *
     * @return a string
     */
    @Override
    public String toString() {
        if (!this.getDone()) {
            return "[T][ ] " + this.getName();
        } else {
            return "[T][X] " + this.getName();
        }
    }
}

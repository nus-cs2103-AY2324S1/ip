package dot.tasks;

import java.time.LocalDateTime;

/**
 * The Todo class implements the Todo Task which instances
 * can be inserted into a TaskList with simply a description.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description This is the description for the Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * This is the overloaded Constructor for Todo.
     *
     * @param description This is the description for the Task.
     * @param isCompleted This is the boolean representing the completeness of the Todo.
     */
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String getFileFormat() {
        return String.format("T | %s", super.getFileFormat());
    }

    @Override
    public boolean isOnDate(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        assert startOfDay.isBefore(endOfDay) : "startOfDay should be before endOfDay";
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

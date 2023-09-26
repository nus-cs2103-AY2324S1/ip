package taskutil;

import java.time.LocalDateTime;

/**
 * Class for To-do task.
 */
public class Todo extends Task {

    /**
     * Constructor for To-do object, using super constructor.
     *
     * @param title Description of task.
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * Returns true by default as To-do tasks have no assigned time.
     *
     * @param localDateTime DateTime to be considered.
     * @return True.
     */
    public boolean isUnderSchedule(LocalDateTime localDateTime) {
        return true;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts To-do task to a string for storing in data file.
     *
     * @return Formatted string with data for To-do task.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}

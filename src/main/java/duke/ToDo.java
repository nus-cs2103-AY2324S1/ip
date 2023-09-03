package duke;

import java.time.LocalDate;

/**
 * A Todo type task that can be handled by the chatbot.
 */
public class ToDo extends Task {
    /**
     * Constructor for the ToDo class.
     *
     * @param name Name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the task to be saved.
     *
     * @return A string representing the todo to be saved.
     */
    @Override
    public String toSaveStateString() {
        String[] state = new String[]{ "todo", this.getDone() ? "1" : "0", this.getTaskName() };
        return String.join(" / ", state);
    }

    /**
     * Returns whether the task is on a date.
     *
     * @return A boolean representation of whether the task is on a given date.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }
}

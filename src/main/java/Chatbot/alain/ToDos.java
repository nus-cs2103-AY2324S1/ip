package chatbot.alain;

import java.time.LocalDate;

/**
 * Represents a task of type "To-Do" without a specific deadline or time.
 */
public class ToDos extends Task {

    /**
     * Constructs a To-Do task.
     *
     * @param description Description of the to-do task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the To-Do task.
     *
     * @return String representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public void setTime(LocalDate date, boolean by) {
        try {
            throw new AlainException("A todo Task should not have time limit");
        } catch (AlainException e) {
            Ui.showError(e.getMessage());
        }
    }
    @Override
    public LocalDate getDate() {
        return LocalDate.MAX;
    }
}


package main.java;

import java.util.Optional;

/**
 * Class that represents a task that the user adds to Juke.
 */
public abstract class JukeTask extends JukeObject {
    /** Task description. */
    private String taskName;

    /** Boolean to check if the task is completed */
    private boolean isCompleted;

    /** Icon to display when the task is completed. */
    private static final String COMPLETED_INDICATOR = "[✓] ";

    /** Icon to display when the task not completed. */
    private static final String INCOMPLETE_INDICATOR = "[ ] ";

    /**
     * Private constructor used to create an incomplete task.
     * @param taskName Task description
     */
    public JukeTask(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Private constructor used to create an incomplete task.
     * @param taskName Task description
     * @param isCompleted Boolean to describe if the task is completed or otherwise
     */
    private JukeTask(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks a task as complete.
     * @return Optional<? extends JukeAction> for further actions to take
     */
    public Optional<? extends JukeAction> markAsComplete() throws DukeException {
        if (this.isCompleted) {
            throw new DukeException("Oh no! The task selected is already completed!");
        } else {
            this.isCompleted = true;
            return Optional.empty();
        }
    }

    /**
     * Marks a task as incomplete.
     * @return Optional<? extends JukeAction> for further actions to take
     */
    public Optional<? extends JukeAction> markAsIncomplete() throws DukeException {
        if (!this.isCompleted) {
            throw new DukeException("The task selected is already not completed!");
        } else {
            this.isCompleted = false;
            return Optional.empty();
        }
    }

    /**
     * Converts the task object to a String representation.
     * @return String representation of JukeTask
     */
    @Override
    public String toString() {
        return (this.isCompleted ? JukeTask.COMPLETED_INDICATOR : JukeTask.INCOMPLETE_INDICATOR) + taskName;
    }
}

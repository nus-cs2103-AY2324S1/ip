package main.java.tasks;

import main.java.actions.JukeAction;
import main.java.exceptions.JukeException;
import main.java.primitivies.JukeObject;

import java.util.Optional;

/**
 * Abstract Class that represents a task that the user adds to Juke.
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
     * Marks a task as complete.
     * @return {@code Optional<? extends JukeAction>} for further actions to take
     */
    public void markAsComplete() throws JukeException {
        if (this.isCompleted) {
            throw new JukeException("Oh no! The task selected is already completed!");
        } else {
            this.isCompleted = true;
        }
    }

    /**
     * Marks a task as incomplete.
     * @return {@code Optional<? extends JukeAction>} for further actions to take
     */
    public void markAsIncomplete() throws JukeException {
        if (!this.isCompleted) {
            throw new JukeException("The task selected is already not completed!");
        } else {
            this.isCompleted = false;
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

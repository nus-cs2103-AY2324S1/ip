package main.java.tasks;

import main.java.Juke;
import main.java.JukeObject;
import main.java.actions.JukeAction;
import main.java.actions.JukeErrorAction;

import java.util.Optional;

/**
 * Class that represents a task that the user adds to Juke.
 */
public class JukeTask extends JukeObject {
    /** Task description. */
    private String taskName;

    /** Boolean to check if the task is completed */
    private boolean isCompleted;

    /** Icon to display when the task is completed. */
    private static final String COMPLETED_INDICATOR = "[✓] ";

    /** Icon to display when the task not completed. */
    private static final String INCOMPLETE_INDICATOR = "[ ] ";

    /**
     * Factory method to create an incomplete task.
     * @param task Task description
     * @return Incomplete JukeTask
     */
    public static JukeTask of(String task) {
        return new JukeTask(task);
    }

    /**
     * Factory method to create a completed task.
     * @param task Task description
     * @return Completed JukeTask
     */
    public static JukeTask ofCompleted(String task) {
        return new JukeTask(task, true);
    }

    /**
     * Private constructor used to create an incomplete task.
     * @param taskName Task description
     */
    private JukeTask(String taskName) {
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
    public Optional<? extends JukeAction> markAsComplete() {
        if (this.isCompleted) {
            return Optional.of(new JukeErrorAction("The task selected is already completed!"));
        } else {
            this.isCompleted = true;
            return Optional.empty();
        }
    }

    /**
     * Marks a task as incomplete.
     * @return Optional<? extends JukeAction> for further actions to take
     */
    public Optional<? extends JukeAction> markAsIncomplete() {
        if (!this.isCompleted) {
            return Optional.of(new JukeErrorAction("The task selected is already not completed!"));
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

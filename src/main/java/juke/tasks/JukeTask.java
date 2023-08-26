package main.java.juke.tasks;

import main.java.juke.exceptions.JukeStateException;
import main.java.juke.primitivies.JukeObject;

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

    /** Abstract method that dictates how a particular task should be saved. */
    public String save() {
        return (this.isCompleted ? "|T|" : "|F|") + this.taskName;
    }

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
     * @throws {@code JukeStateException} if the task is already completed
     */
    public void markAsComplete() throws JukeStateException {
        if (this.isCompleted) {
            throw new JukeStateException("Oh no! The task selected is already completed!");
        } else {
            this.isCompleted = true;
        }
    }

    /**
     * Marks a task as incomplete.
     * @throws {@code JukeStateException} if the task is already incomplete
     */
    public void markAsIncomplete() throws JukeStateException {
        if (!this.isCompleted) {
            throw new JukeStateException("The task selected is already not completed!");
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

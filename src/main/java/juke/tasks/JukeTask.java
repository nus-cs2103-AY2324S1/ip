package juke.tasks;

import juke.commons.classes.JukeObject;
import juke.exceptions.JukeStateException;

/**
 * Abstract Class that represents a task that the user adds to {@code Juke}.
 */
public abstract class JukeTask extends JukeObject {
    /** Icon to display when the task is completed. */
    private static final String COMPLETED_INDICATOR = "[✓] ";

    /** Icon to display when the task not completed. */
    private static final String INCOMPLETE_INDICATOR = "[ ] ";

    /** Task description. */
    private final String taskName;

    /** Boolean to check if the task is completed */
    private boolean isCompleted;

    /**
     * Creates an instance of {@code JukeTask}.
     *
     * @param taskName Task description
     */
    public JukeTask(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Marks a task as complete.
     *
     * @throws JukeStateException if the task is already completed
     */
    public void setAsComplete() throws JukeStateException {
        if (this.isCompleted) {
            throw new JukeStateException("Oh no! The task selected is already completed!");
        } else {
            this.isCompleted = true;
        }
    }

    /**
     * Marks a task as incomplete.
     *
     * @throws JukeStateException if the task is already incomplete
     */
    public void setAsIncomplete() throws JukeStateException {
        if (!this.isCompleted) {
            throw new JukeStateException("The task selected is already not completed!");
        } else {
            this.isCompleted = false;
        }
    }

    /**
     * Converts the task object to a String representation.
     *
     * @return String representation of JukeTask
     */
    @Override
    public String toString() {
        return (this.isCompleted ? JukeTask.COMPLETED_INDICATOR : JukeTask.INCOMPLETE_INDICATOR) + taskName;
    }

    /**
     * Returns the string which represents this object when it is saved into the datafile.
     *
     * @return Datafile representation of this object
     */
    public String save() {
        return (this.isCompleted ? "|T|" : "|F|") + this.taskName;
    }

    /**
     * Checks if the task description contains the word being queried. All subclassses of
     * {@code JukeTask} will reuse this method for checking.
     *
     * @param word Keyword to check
     * @return true if the task description contains the keyword, false otherwise
     */
    public boolean stringMatches(String word) {
        return this.taskName.contains(word);
    }
}

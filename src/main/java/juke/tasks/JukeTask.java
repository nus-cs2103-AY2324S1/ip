package juke.tasks;

import juke.commons.classes.JukeObject;
import juke.commons.enums.SortTypeEnum;
import juke.commons.exceptions.JukeStateException;
import juke.commons.interfaces.Savable;
import juke.commons.interfaces.TaskSortable;


/**
 * Abstract Class that represents a task that the user can add to Juke.
 */
public abstract class JukeTask extends JukeObject implements TaskSortable<JukeTask>, Savable {
    /** String to represent when the task is completed. */
    private static final String COMPLETED_INDICATOR = "[✓] ";

    /** String to represent when the task not completed. */
    private static final String INCOMPLETE_INDICATOR = "[ ] ";

    /** Task description. */
    private final String taskName;

    /** Boolean to check if the task is completed */
    private boolean isCompleted;

    /**
     * Creates an instance of {@code JukeTask}. Since this is an abstract method,
     * this method is purely for subclasses to call for initialising common aspects of
     * the tasks.
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
     * Returns the String which represents this object when it is saved into the datafile.
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

    /**
     * Converts the task object to its corresponding String representation.
     *
     * @return String representation of JukeTask
     */
    @Override
    public String toString() {
        return (this.isCompleted ? JukeTask.COMPLETED_INDICATOR : JukeTask.INCOMPLETE_INDICATOR) + taskName;
    }

    /**
     * Compares this {@code JukeTask} object with the specified {@code JukeTask} object for order. This method
     * compares task description only.
     *
     * <p>This method is present mainly for polymorphism and method reuse.</p>
     *
     * <p>Do note that this method's outputs are inverted to ensure proper sort order.</p>
     *
     * @param task the {@code JukeTask} object to be compared with
     * @param sortType the type of sort to perform on the tasks
     * @return -1 if this {@code JukeTask} object is before the {@code JukeTask} object passed in, 0 if they
     *     are the same, and 1 if this {@code JukeTask} object is after the {@code JukeTask} object passed in
     */
    @Override
    public int sortBy(JukeTask task, SortTypeEnum sortType) {
        return this.taskName.compareTo(task.taskName) * -1;
    }
}

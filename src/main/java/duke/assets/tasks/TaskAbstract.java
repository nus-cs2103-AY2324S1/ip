package duke.assets.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.dukeexceptions.StateCannotBeAlteredException;

/**
 * Abstract class that represents a task
 */
public abstract class TaskAbstract {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task
     *
     * @param description - description of task
     */
    public TaskAbstract(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as complete
     *
     * @throws StateCannotBeAlteredException if task is already complete
     */
    public void completeTask() throws StateCannotBeAlteredException {
        if (this.isDone) {
            throw new StateCannotBeAlteredException();
        } else {
            this.isDone = true;
        }
    }

    /**
     * Mark the task as incomplete
     *
     * @throws StateCannotBeAlteredException if task is already incomplete
     */
    public void undo() throws StateCannotBeAlteredException {
        if (!this.isDone) {
            throw new StateCannotBeAlteredException();
        } else {
            this.isDone = false;
        }
    }

    /**
     * Marks a newly created task as complete
     */
    public void completeNewTask() {
        this.isDone = true;
    }

    /**
     * Checks if the task description contains the given token
     *
     * @param token token to be checked against
     * @return true if task description contains token, false otherwise
     */
    public boolean hasToken(String token) {
        return this.description.contains(token);
    }

    /**
     * Compare the deadline date of the two tasks
     *
     * @param other other task
     * @param reverse true for descending order, false for ascending order
     * @return a negative integer if this task has ordering priority over the other, 0 if both are chronologically
     *     equivalent and a positive integer if other task has ordering priority over this one
     */
    public int compareDate(TaskAbstract other, boolean reverse) {
        int dateComparison = this.getDate(reverse).compareTo(other.getDate(reverse));
        int timeComparison = this.getTime(reverse).compareTo(other.getTime(reverse));
        int reverseMultiplicator = reverse ? -1 : 1;
        return dateComparison == 0 ? (timeComparison == 0 ? compareInformation(other, false)
                : reverseMultiplicator * timeComparison) : reverseMultiplicator * dateComparison;
    }

    /**
     * Compare the information of two tasks lexicographically
     *
     * @param other other task
     * @param reverse true for descending order, false for ascending order
     * @return a negative integer if this task has ordering priority over the other, 0 if both are lexicographically
     *     equivalent and a positive integer if the other task has ordering priority over this one
     */
    public int compareInformation(TaskAbstract other, boolean reverse) {
        int informationComparison = this.description.compareTo(other.description);
        return informationComparison * (reverse ? -1 : 1);
    }

    /**
     * Get the deadline date of the task
     *
     * @param reverse true for descending order, false for ascending order
     * @return the deadline date of the task
     */
    protected abstract LocalDate getDate(boolean reverse);

    /**
     * Get the deadline time of the task
     *
     * @param reverse true for descending order, false for ascending order
     * @return the deadline time of the task
     */
    protected abstract LocalTime getTime(boolean reverse);

    /**
     * Get status of the task as a string
     *
     * @return status of the task a string
     */
    public abstract String getStatus();

    /**
     * Formats the task into a string that is ready for saving into memory
     *
     * @return formatted string that is ready for saving into memory
     */
    public abstract String saveToTextFormat();

    @Override
    public String toString() {
        return this.description;
    }
}

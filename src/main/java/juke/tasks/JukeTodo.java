package juke.tasks;

import juke.commons.enums.SortOrderEnum;
import juke.commons.enums.SortTypeEnum;
import juke.exceptions.arguments.JukeIllegalArgumentException;

/**
 * Represents a Todo task. Todo tasks do not have any deadlines.
 */
public class JukeTodo extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[T] ";

    /**
     * Constructor to create a {@code JukeTodo}.
     *
     * @param task Task description
     */
    public JukeTodo(String task) {
        super(task);
    }

    /**
     * Creates an instance of {@code JukeTodo}.
     *
     * @param task Task description
     * @param isCompleted Status of completion of the task
     */
    public JukeTodo(String task, boolean isCompleted) {
        super(task);

        if (isCompleted) {
            this.setAsComplete();
        }
    }

    /**
     * Returns the string which represents this object when it is saved into the datafile.
     *
     * @return Datafile representation of this object
     */
    @Override
    public String save() {
        return "T" + super.save();
    }

    /**
     * Compares this {@code JukeTodo} object with the specified {@code JukeTask} object.
     *
     * @param task the {@code JukeTask} object to be compared with
     * @param sortOrder the order to sort the tasks by
     * @param sortType the type of sort to perform on the tasks
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     *     the specified object.
     */
    @Override
    public int sortBy(JukeTask task, SortOrderEnum sortOrder, SortTypeEnum sortType) {
        switch (sortType) {
        case DESCRIPTION:
            return super.sortBy(task, sortOrder, sortType);
        case DEADLINE:
        case END_DATE:
        case START_DATE:
            return this.compareStartDate(task, sortOrder);
        default:
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list on that field!");
        }
    }

    /**
     * Compares the start date between this {@code JukeTodo} object with another {@code JukeTask} object.
     *
     * @param task the {@code JukeTask} object to be compared with
     * @param sortOrder the order to sort the tasks by
     * @return -1 if this {@code JukeDeadline} object is before the {@code JukeTask} object passed in, 0 if they are the
     *     same, and 1 if this {@code JukeDeadline} object is after the {@code JukeTask} object passed in
     */
    private int compareStartDate(JukeTask task, SortOrderEnum sortOrder) {
        if (task instanceof JukeEvent || task instanceof JukeDeadline) {
            // todos are assumed to have an infinitely early start date
            // and is hence always before any event task; and
            // deadlines have a larger but infinitely early start date
            return -1 * sortOrder.getMultiplier();
        } else if (task instanceof JukeTodo) {
            // todos are of equal priority when compared to other todos
            return 0;
        } else {
            // should not reach here, unless there are other unknown subclasses of JukeTask
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list with an unknown task within it!");
        }
    }

    /**
     * Returns String representation of this {@code JukeTodo} object
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return JukeTodo.TASK_DESCRIPTOR + super.toString();
    }
}

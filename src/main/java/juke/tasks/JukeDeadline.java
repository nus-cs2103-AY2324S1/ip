package juke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import juke.commons.enums.SortOrderEnum;
import juke.commons.enums.SortTypeEnum;
import juke.exceptions.JukeStateException;
import juke.exceptions.arguments.JukeIllegalArgumentException;

/**
 * Represents a Deadline task. Deadline tasks contain a deadline which is
 * represented by a {@code LocalDateTime} object.
 */
public class JukeDeadline extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[D] ";

    /** Deadline for Task. */
    private final LocalDateTime deadline;

    /**
     * Creates an instance of {@code JukeDeadline}.
     *
     * @param task Task description
     * @param deadline Deadline for task
     */
    public JukeDeadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    /**
     * Creates an instance of {@code JukeDeadline}.
     *
     * @param task Task description
     * @param deadline Deadline for task
     * @param isCompleted Status of completion of the task
     * @throws JukeStateException if the task is already completed
     */
    public JukeDeadline(String task, LocalDateTime deadline, boolean isCompleted) {
        this(task, deadline);

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
        return "D" + super.save() + "|" + deadline;
    }

    /**
     * Compares this {@code JukeDeadline}'s deadline with respect to the localDateTime object passed in. This method
     * is mainly used for sorting, and should not be invoked directly by the user.
     *
     * @param localDateTime {@code LocalDateTime} object to compare with
     * @return -1 if this {@code JukeDeadline}'s deadline is before the localDateTime object passed in, 0 if they are
     *     the same, and 1 if this {@code JukeDeadline}'s deadline is after the localDateTime object passed in
     */
    public int compareDeadline(LocalDateTime localDateTime) {
        return localDateTime.compareTo(this.deadline);
    }

    /**
     * Compares this {@code JukeTask} object with the specified {@code JukeTask} object for order.
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
            return this.compareDeadlineOrEndDate(task, sortOrder);
        case START_DATE:
            return this.compareStartDate(task, sortOrder);
        default:
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list on that field!");
        }
    }

    /**
     * Compares the start date between this {@code JukeDeadline} object with another {@code JukeTask} object.
     *
     * @param task the {@code JukeTask} object to be compared with
     * @param sortOrder the order to sort the tasks by
     * @return -1 if this {@code JukeDeadline} object is before the {@code JukeTask} object passed in, 0 if they are the
     *     same, and 1 if this {@code JukeDeadline} object is after the {@code JukeTask} object passed in
     */
    private int compareStartDate(JukeTask task, SortOrderEnum sortOrder) {
        if (task instanceof JukeEvent) {
            // deadlines are assumed to have an infinitely early start date
            // and is hence always before any event task
            return -1 * sortOrder.getMultiplier();
        } else if (task instanceof JukeDeadline) {
            // deadlines are of equal priority when compared to other deadlines
            return 0;
        } else if (task instanceof JukeTodo) {
            // todos are assumed to have a smaller but infinitely early start date
            // and are hence before after any deadline task
            return sortOrder.getMultiplier();
        } else {
            // should not reach here, unless there are other unknown subclasses of JukeTask
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list with an unknown task within it!");
        }
    }

    /**
     * Compares this {@code JukeDeadline}'s deadline or end date with respect to the input {@code JukeEvent} object.
     *
     * @param task the {@code JukeTask} object to be compared with
     * @param sortOrder the order to sort the tasks by
     * @return -1 if this {@code JukeDeadline}'s deadline or end date is before the {@code JukeEvent} object passed in,
     *     0 if they are the same, and 1 if this {@code JukeDeadline}'s deadline or end date is after the
     *     {@code JukeEvent} object passed in
     */
    private int compareDeadlineOrEndDate(JukeTask task, SortOrderEnum sortOrder) {
        if (task instanceof JukeEvent) {
            JukeEvent event = (JukeEvent) task;
            return event.compareEndTime(this.deadline) * sortOrder.getMultiplier();
        } else if (task instanceof JukeDeadline) {
            JukeDeadline deadline = (JukeDeadline) task;
            return this.deadline.compareTo(deadline.deadline) * sortOrder.getMultiplier();
        } else if (task instanceof JukeTodo) {
            // since todos have an infinitely early deadline, they are always considered to be before events
            return sortOrder.getMultiplier();
        } else {
            // should not reach here, unless there are other unknown subclasses of JukeTask
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list with an unknown task within it!");
        }
    }

    /**
     * Returns String representation of this {@code JukeDeadline} object
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return JukeDeadline.TASK_DESCRIPTOR
                + super.toString()
                + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm"))
                + " hrs)";
    }
}

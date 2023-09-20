package juke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import juke.commons.DateUtils;
import juke.commons.enums.SortTypeEnum;
import juke.commons.exceptions.JukeStateException;
import juke.commons.exceptions.arguments.JukeIllegalArgumentException;

/**
 * Represents a Deadline task. Deadline tasks contain a deadline which is represented by a
 * {@code LocalDateTime} object.
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
     * Returns the String which represents this object when it is saved into the datafile.
     *
     * @return Datafile representation of this object
     */
    @Override
    public String save() {
        return "D" + super.save() + "|" + deadline;
    }

    /**
     * Compares this {@code JukeTask} object with the input {@code JukeTask} object for order. This method should
     * not be directly invoked by the user as it is mainly used for sorting.
     *
     * @param task the {@code JukeTask} object to be compared with
     * @param sortType the type of sort to perform on the tasks
     * @return -1 if this {@code JukeDeadline} object is before the {@code JukeTask} object passed in, 0 if they
     *     are the same, and 1 if this {@code JukeDeadline} object is after the {@code JukeTask} object passed in
     */
    @Override
    public int sortBy(JukeTask task, SortTypeEnum sortType) {
        switch (sortType) {
        case DESCRIPTION:
            // reuses the superclass's description comparator method
            return super.sortBy(task, sortType);
        case DEADLINE:
        case END_DATE:
            return this.compareDeadlineOrEndDate(task);
        case START_DATE:
            return this.compareStartDate(task);
        default:
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list on that field!");
        }
    }

    /**
     * Compares the start date between this {@code JukeDeadline} object with another input {@code JukeTask} object.
     * This method should not be directly invoked by the user as it is mainly used for sorting.
     *
     * @param task the {@code JukeTask} object to be compared with
     * @return -1 if this {@code JukeDeadline} object's start date is before the start date of the
     *     input {@code JukeTask} object, 0 if they are the same, and 1 if this {@code JukeDeadline}'s
     *     start date is after the start date of the input {@code JukeTask} object
     */
    private int compareStartDate(JukeTask task) {
        if (task instanceof JukeEvent) {
            // deadlines are assumed to have an infinitely early start date
            // and is hence always before any event task
            return -1;
        } else if (task instanceof JukeDeadline) {
            // deadlines are of equal priority when compared to other deadlines
            return 0;
        } else if (task instanceof JukeTodo) {
            // todos are assumed to have a smaller but infinitely early start date
            // and are hence before after any deadline task
            return 1;
        } else {
            // should not reach here, unless there are other unknown subclasses of JukeTask
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list with an unknown task within it!");
        }
    }

    /**
     * Compares the deadline or end date of this {@code JukeDeadline} object
     * with another input {@code JukeEvent} object.
     *
     * @param task the {@code JukeTask} object to be compared with
     * @return -1 if this {@code JukeDeadline} object's deadline is before the deadline/end date of the
     *     input {@code JukeTask} object, 0 if they are the same, and 1 if this {@code JukeDeadline}'s
     *     deadline is after the deadline/end date of the input {@code JukeTask} object
     */
    private int compareDeadlineOrEndDate(JukeTask task) {
        if (task instanceof JukeEvent) {
            return DateUtils.compareDateTimes(this.deadline, ((JukeEvent) task).getEndDate());
        } else if (task instanceof JukeDeadline) {
            return DateUtils.compareDateTimes(this.deadline, ((JukeDeadline) task).deadline);
        } else if (task instanceof JukeTodo) {
            // since todos have an infinitely early deadline, they are always considered to be before deadlines
            return 1;
        } else {
            // should not reach here, unless there are other unknown subclasses of JukeTask
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list with an unknown task within it!");
        }
    }

    /**
     * Returns the deadline of this {@code JukeDeadline} object.
     *
     * @return End date of this {@code JukeEvent} object.
     */
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Returns String representation of this {@code JukeDeadline} object.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return JukeDeadline.TASK_DESCRIPTOR
                + super.toString()
                + " (by "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm"))
                + " hrs)";
    }
}

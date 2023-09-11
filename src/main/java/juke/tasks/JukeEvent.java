package juke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import juke.commons.enums.SortOrderEnum;
import juke.commons.enums.SortTypeEnum;
import juke.commons.interfaces.TaskSortable;
import juke.exceptions.JukeStateException;
import juke.exceptions.arguments.JukeIllegalArgumentException;

/**
 * Represents an Event task. Event tasks contain both a startTime and endTime
 * deadline, which are represented by {@code LocalDateTime} objects.
 */
public class JukeEvent extends JukeTask implements TaskSortable<JukeTask> {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[E] ";

    /** Start Time. */
    private final LocalDateTime startTime;

    /** End Time. */
    private final LocalDateTime endTime;

    /**
     * Creates an instance of {@code JukeEvent}.
     *
     * @param taskName Task description
     * @param startTime Start date/time
     * @param endTime End date/time
     */
    public JukeEvent(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates an instance of {@code JukeEvent}.
     *
     * @param taskName Task description
     * @param startTime Start date/time
     * @param endTime End date/time
     * @param isCompleted Status of completion of the task
     * @throws JukeStateException if the task is already completed
     */
    public JukeEvent(String taskName, LocalDateTime startTime, LocalDateTime endTime, boolean isCompleted) {
        this(taskName, startTime, endTime);

        if (isCompleted) {
            // the event should have a start date that is before the end date
            assert startTime.isBefore(endTime);
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
        return "E" + super.save() + "|" + startTime + "|" + endTime;
    }

    /**
     * Compares the input {@code LocalDateTime} object with the end time of this {@code JukeDeadline} object. This
     * method is mainly used for sorting, and should not be invoked directly by the user.
     *
     * @param dateTime input {@code LocalDateTime} object
     * @return -1 if the input {@code LocalDateTime} object is before the end time of this {@code JukeDeadline} object,
     *     0 if they are the same, and 1 if the input {@code LocalDateTime} object is after the end time of this
     *     deadline
     */
    public int compareEndTime(LocalDateTime dateTime) {
        return dateTime.compareTo(this.endTime);
    }

    /**
     * Compares this {@code JukeEvent} object with the specified {@code JukeTask} object.
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
     * Compares this {@code JukeDeadline}'s deadline or end date with respect to the input {@code JukeEvent} object.
     * This method exists only for sorting.
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
            return this.endTime.compareTo(event.endTime) * sortOrder.getMultiplier();
        } else if (task instanceof JukeDeadline) {
            JukeDeadline deadline = (JukeDeadline) task;
            int otherDeadlineComparedToSelf = deadline.compareDeadline(this.endTime);
            return otherDeadlineComparedToSelf * sortOrder.getMultiplier();
        } else if (task instanceof JukeTodo) {
            // since todos have an infinitely early deadline, they are always considered to be before events
            // and so events are always after todos, subjected to the constraints of the sort order
            return sortOrder.getMultiplier();
        } else {
            // should not reach here, unless there are other unknown subclasses of JukeTask
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list with an unknown task within it!");
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
            JukeEvent event = (JukeEvent) task;
            return this.startTime.compareTo(event.startTime) * sortOrder.getMultiplier();
        } else if (task instanceof JukeDeadline || task instanceof JukeTodo) {
            // since todos have an infinitely early deadline, they are always considered to be before events
            // and so events are always after todos, subjected to the constraints of the sort order
            return sortOrder.getMultiplier();
        } else {
            // should not reach here, unless there are other unknown subclasses of JukeTask
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list with an unknown task within it!");
        }
    }

    /**
     * Returns String representation of this {@code JukeEvent} object
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return JukeEvent.TASK_DESCRIPTOR
                + super.toString()
                + " (from " + startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm"))
                + " hrs to " + endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + " hrs)";
    }
}

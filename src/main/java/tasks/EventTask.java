package tasks;

import java.time.LocalDate;
import java.util.Objects;

import enums.TaskType;

/**
 * The `EventTask` class represents an event task with a specific start and end date in the Woof application.
 * It extends the `Task` class and includes the event date range information.
 */
public class EventTask extends Task {
    /**
     * The start date of the event task.
     */
    private final LocalDate startDate;

    /**
     * The end date of the event task.
     */
    private final LocalDate endDate;

    /**
     * Constructs an `EventTask` with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param startDate   The start date of the event task.
     * @param endDate     The end date of the event task.
     */
    public EventTask(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an `EventTask` with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param startDate   The start date of the event task.
     * @param endDate     The end date of the event task.
     * @param isDone      The optional is done to mark a task as per is done.
     */
    public EventTask(String description, LocalDate startDate, LocalDate endDate, Boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets the event date range formatted as a string.
     *
     * @return A string representation of the event date range.
     */
    public String getEventRange() {
        return String.format("%s~From: %s\n%s~To  : %s",
            getTabStopTwo(),
            parseDateTimeOut(startDate),
            getTabStopTwo(),
            parseDateTimeOut(endDate)
        );
    }

    /**
     * Retrieves the type of task associated with this object, which is "DEADLINE."
     *
     * @return The task type, which is "EVENT" for objects of this class.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Retrieves the datetime associated with events, the start date and time, represented as a long value.
     *
     * @return The start date and time as a long value.
     */
    @Override
    public long getDateTimeLong() {
        return this.startDate.toEpochDay();
    }

    /**
     * Generates a string representation of the `EventTask`.
     *
     * @return A string representation of the task, including its symbol, description, and event date range.
     */
    @Override
    public String toString() {
        return String.format("%s%s%s\n", TaskType.EVENT.toSymbol() , super.toString() , getEventRange());
    }

    /**
     * Checks if this `EventTask` is equal to another object.
     *
     * @param o The object to compare to.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventTask)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        EventTask eventTask = (EventTask) o;
        return this.startDate.equals(eventTask.startDate) && this.endDate.equals(eventTask.endDate);
    }

    /**
     * Generates a hash code for this `EventTask`.
     *
     * @return A hash code for the task, including its description and event date range.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.startDate, this.endDate);
    }
}

package corgi.tasks;

import java.time.LocalDate;

import corgi.parsers.TaskParser;

/**
 * Event task, a type of task that start at a specific date/time and ends at a specific date/time.
 */
public final class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Initializes a new event with the given description and duration.
     *
     * @param desc The description of the event
     * @param from The start date/time of the event
     * @param to The end date/time of the event
     */
    public Event(String desc, LocalDate from, LocalDate to) {
        super(false, desc);
        this.from = from;
        this.to = to;
    }


    /**
     * Initializes a new event task with the given status, description, start date, and end date.
     *
     * @param isDone The status of the task.
     * @param desc The description of the task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(boolean isDone, String desc, LocalDate from, LocalDate to) {
        super(isDone, desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public Event markAsDone() throws TaskStatusException {
        if (this.isDone) {
            throw new TaskStatusException("The task is already marked as done.");
        }
        return new Event(true, desc, from, to);
    }

    @Override
    public Event markAsNotDone() throws TaskStatusException {
        if (!this.isDone) {
            throw new TaskStatusException("The task is already marked as not done.");
        }
        return new Event(false, desc, from, to);
    }

    /**
     * Checks if the event task is happening on the specified target date.
     *
     * @param targetDate The date to check against the event duration.
     * @return True if the task's event duration includes the target date, false otherwise.
     */
    public boolean isHappeningOnDate(LocalDate targetDate) {
        boolean isOnStartDate = targetDate.isEqual(this.from);
        boolean isOnEndDate = targetDate.isEqual(this.to);
        boolean isWithinEventDateRange = (targetDate.isAfter(this.from) && targetDate.isBefore(this.to));

        return isOnStartDate || isOnEndDate || isWithinEventDateRange;
    }

    /**
     * Converts the event task to a storable string representation.
     *
     * @return A string representing the event task in a storable format.
     */
    @Override
    public String toStorableString() {
        String statusStr = this.isDone ? "1" : "0";
        String formattedFrom = this.from.format(Task.DATE_INPUT_FORMATTER);
        String formattedTo = this.to.format(Task.DATE_INPUT_FORMATTER);

        String[] infos = {"E", statusStr, this.desc, formattedFrom, formattedTo};
        String combinedInfos = String.join(TaskParser.SEPARATOR, infos);

        return combinedInfos;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return Task type, status icon, description, start date/time and end date/time of the task
     */
    @Override
    public String toString() {
        String dateRange = "(from: " + this.from.format(DATE_OUTPUT_FORMATTER)
                + " to: " + this.to.format(DATE_OUTPUT_FORMATTER) + ")";
        return "[E]" + super.toString() + " " + dateRange;
    }
}

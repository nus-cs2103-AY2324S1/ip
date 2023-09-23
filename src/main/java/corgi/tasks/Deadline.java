package corgi.tasks;

import java.time.LocalDate;

import corgi.parsers.TaskParser;

/**
 * Deadline task, a type of task that need to be done before a specific date/time.
 */
public final class Deadline extends Task {
    private final LocalDate by;

    /**
     * Initializes a new deadline task with the given description and deadline.
     *
     * @param desc The description of the task
     * @param by The deadline of the task
     */
    public Deadline(String desc, LocalDate by) {
        super(false, desc);
        this.by = by;
    }

    /**
     * Initializes a new deadline task with the given status, description, and deadline.
     *
     * @param status The status of the task.
     * @param desc The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(boolean isDone, String desc, LocalDate by) {
        super(isDone, desc);
        this.by = by;
    }

    @Override
    public Deadline markAsDone() throws TaskStatusException {
        if (this.isDone) {
            throw new TaskStatusException("The task is already marked as done.");
        }
        return new Deadline(true, desc, by);
    }

    @Override
    public Deadline markAsNotDone() throws TaskStatusException {
        if (!this.isDone) {
            throw new TaskStatusException("The task is already marked as not done.");
        }
        return new Deadline(false, desc, by);
    }

    /**
     * Checks if the deadline task is happening on the specified target date.
     *
     * @param targetDate The date to check against the deadline.
     * @return True if the task's deadline matches the target date, false otherwise.
     */
    public boolean isHappeningOnDate(LocalDate targetDate) {
        return this.by.isEqual(targetDate);
    }

    /**
     * Converts the deadline task to a storable string representation.
     *
     * @return A string representing the deadline task in a storable format.
     */
    @Override
    public String toStorableString() {
        String statusStr = this.isDone ? "1" : "0";
        String formattedBy = this.by.format(Task.DATE_INPUT_FORMATTER);

        String[] infos = {"D", statusStr, this.desc, formattedBy};
        String combinedInfos = String.join(TaskParser.SEPARATOR, infos);

        return combinedInfos;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return Task type, status icon, description and deadline of the task
     */
    @Override
    public String toString() {
        String deadline = "(by: " + this.by.format(Task.DATE_OUTPUT_FORMATTER) + ")";

        return "[D]" + super.toString() + " " + deadline;
    }
}

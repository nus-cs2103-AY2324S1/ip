package corgi.tasks;

import java.time.LocalDate;

import corgi.parsers.TaskParser;

/**
 * Deadline task, a type of task that need to be done before a specific date/time.
 */
public class Deadline extends Task{
    private LocalDate by;

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

    public Deadline(boolean status, String desc, LocalDate by) {
        super(status, desc);
        this.by = by;
    }

    public boolean isHappeningOnDate(LocalDate targetDate) {
        return this.by.isEqual(targetDate);
    }

    @Override
    public String toStorableString() {
        String[] infos = {"D", this.status ? "1" : "0", this.desc, this.by.format(Task.DATE_INPUT_FORMATTER)};

        return String.join(TaskParser.SEPARATOR, infos);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return Task type, status icon, description and deadline of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Task.DATE_OUTPUT_FORMATTER) + ")";
    }
}

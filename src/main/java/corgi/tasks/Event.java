package corgi.tasks;

import java.time.LocalDate;

import corgi.parsers.TaskParser;

/**
 * Event task, a type of task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task{
    private LocalDate from;
    private LocalDate to;
    
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
     * @param status The status of the task.
     * @param desc The description of the task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(boolean status, String desc, LocalDate from, LocalDate to) {
        super(status, desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks if the event task is happening on the specified target date.
     *
     * @param targetDate The date to check against the event duration.
     * @return True if the task's event duration includes the target date, false otherwise.
     */
    public boolean isHappeningOnDate(LocalDate targetDate) {
        return targetDate.isEqual(this.from) || targetDate.isEqual(this.to) 
                || (targetDate.isAfter(this.from) && targetDate.isBefore(this.to));
    }

    /**
     * Converts the event task to a storable string representation.
     *
     * @return A string representing the event task in a storable format.
     */
    @Override
    public String toStorableString() {
        String[] infos = {"E", this.status ? "1" : "0", this.desc, 
                this.from.format(Task.DATE_INPUT_FORMATTER), this.to.format(Task.DATE_INPUT_FORMATTER)};

        return String.join(TaskParser.SEPARATOR, infos);
    }

    /**
     * Returns a string representation of the event.
     *
     * @return Task type, status icon, description, start date/time and end date/time of the task
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from.format(DATE_OUTPUT_FORMATTER) 
                + " to: " + this.to.format(DATE_OUTPUT_FORMATTER) + ")";
    }
}

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

    public Event(boolean status, String desc, LocalDate from, LocalDate to) {
        super(status, desc);
        this.from = from;
        this.to = to;
    }

    public boolean isHappeningOnDate(LocalDate targetDate) {
        return targetDate.isEqual(this.from) || targetDate.isEqual(this.to) 
                || (targetDate.isAfter(this.from) && targetDate.isBefore(this.to));
    }

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

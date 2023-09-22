package task;

import command.CommandException;
import util.DateTimeUtil;

import java.time.LocalDateTime;

public class EventTask extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * The constructor for the EventTask class
     *
     * @param name The name of the task
     * @param fromStr The "from" time (as a string)
     * @param toStr The "to" time (as a string)
     *
     * @throws CommandException when fromStr or toStr is not in the correct date-time format,
     * or when the start time (by fromStr) of the event is behind the end time (by toStr) of the event
     */
    public EventTask(String name, String fromStr, String toStr) throws CommandException {
        super(name);
        this.from = DateTimeUtil.parseDateTimeString(fromStr);
        this.to = DateTimeUtil.parseDateTimeString(toStr);
        if(this.from.compareTo(this.to) > 0){
            throw new CommandException("Start time " + DateTimeUtil.dateTimeToString(this.from) + " of this event is behind the end time " + DateTimeUtil.dateTimeToString(this.to) + ".");
        }
    }

    /**
     * Get the string representation of the task.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeUtil.dateTimeToString(this.from )+ " to: " + DateTimeUtil.dateTimeToString(this.to) + ")";
    }
}

package task;

import command.CommandException;
import util.DateTimeUtil;

import java.time.LocalDateTime;

public class EventTask extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask(String name, String fromStr, String toStr) throws CommandException {
        super(name);
        this.from = DateTimeUtil.parseDateTimeString(fromStr);;
        this.to = DateTimeUtil.parseDateTimeString(toStr);
        if(this.from.compareTo(this.to) > 0){
            throw new CommandException("Start time " + DateTimeUtil.dateTimeToString(this.from) + " of this event is behind the end time " + DateTimeUtil.dateTimeToString(this.to) + ".");
        }
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + DateTimeUtil.dateTimeToString(this.from )+ " to: " + DateTimeUtil.dateTimeToString(this.to) + ")";
    }
}

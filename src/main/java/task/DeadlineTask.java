package task;

import command.CommandException;
import main.Main;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    private LocalDateTime deadline;
    public DeadlineTask(String name, String deadlineStr) throws CommandException {
        super(name);
        this.deadline = DateTimeUtil.parseDateTimeString(deadlineStr);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.dateTimeToString(this.deadline) + ")";
    }
}

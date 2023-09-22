package task;

import command.CommandException;
import main.Main;
import util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    private LocalDateTime deadline;

    /**
     * The constructor for the DeadlineTask class
     *
     * @param name The name of the task
     * @param deadlineStr The deadline time (as a string)
     *
     * @throws CommandException when deadlineStr isn't in the correct date-time format
     */
    public DeadlineTask(String name, String deadlineStr) throws CommandException {
        super(name);
        this.deadline = DateTimeUtil.parseDateTimeString(deadlineStr);
    }

    /**
     * Get the string representation of the task.
     *
     * @return string representation of the task
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.dateTimeToString(this.deadline) + ")";
    }
}

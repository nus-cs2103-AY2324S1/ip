package bot.command;

import bot.exception.DateTimeParseBotException;
import bot.exception.IncompleteBotException;

import bot.parsers.DatetimeParser;
import bot.task.TaskList;

import java.time.LocalDateTime;

public class WithinTimelineCommand extends Command {
    private final TaskList taskList;
    private final LocalDateTime localDateTime;

    /**
     * Creates an instance of WithinTimelineCommand object
     *
     * @param list list of all tasks
     * @param time datetime from user input
     * @throws DateTimeParseBotException if the datetime from user input is in wrong format
     */
    public WithinTimelineCommand(TaskList list, String time) throws
            DateTimeParseBotException, IncompleteBotException {
        if (time.isBlank()) {
            throw new IncompleteBotException("OOPS!!! The datetime to check cannot be empty.");
        }
        localDateTime = DatetimeParser.parseTimeInput(time);
        taskList = list.findTasksWithinDateTime(localDateTime);
    }

    /**
     * Executes a series of instructions to find the tasks that have yet to expire and returns
     * the execution output
     *
     * @return String of tasks that are yet to be overdue
     */
    @Override
    public String execute() {
        return this.toString();
    }

    /**
     * Returns a String representation of WithinTimelineCommand
     *
     * @return String representation of WithinTimelineCommand
     */
    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "The following tasks have yet to be overdue:\n" +
                taskList.list() + "\n" +
                Command.SPACER;
    }
}

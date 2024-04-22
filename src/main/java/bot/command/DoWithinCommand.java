package bot.command;

import bot.exception.DateTimeParseBotException;
import bot.exception.IncompleteBotException;

import bot.parsers.DatetimeParser;
import bot.task.TaskList;

import java.time.LocalDateTime;

public class DoWithinCommand extends Command {
    private final TaskList taskList;
    private final LocalDateTime localDateTime;

    /**
     * Creates an instance of DoWithinCommand object
     *
     * @param list list of all tasks
     * @param time datetime from user input
     * @throws DateTimeParseBotException if the datetime from user input is in wrong format
     */
    public DoWithinCommand(TaskList list, String time) throws
            DateTimeParseBotException, IncompleteBotException {
        if (time.isBlank()) {
            throw new IncompleteBotException("OOPS!!! The datetime to check cannot be empty.");
        }
        localDateTime = DatetimeParser.parseTimeInput(time);
        taskList = list.findTasksWithinDateTime(localDateTime);
    }

    /**
     * Executes a series of instructions to find the tasks that need to be done and returns
     * the execution output
     *
     * @return String of tasks that need to be completed
     */
    @Override
    public String execute() {
        return this.toString();
    }

    /**
     * Returns a String representation of DoWithinCommand
     *
     * @return String representation of DoWithinCommand
     */
    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                "The following tasks need to be completed:\n" +
                taskList.list() + "\n" +
                Command.SPACER;
    }
}

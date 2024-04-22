package bot.command;

import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.exception.IncompleteBotException;

import bot.parsers.InputParser;
import bot.task.TaskList;
import bot.task.Event;
import bot.storage.Storage;


public class EventCommand extends Command {

    private final TaskList taskList;
    private final Event event;

    /**
     * Creates an instance of EventCommand object
     *
     * @param taskList the list of tasks
     * @param remainderInfo the uncleaned information pertaining to instancing an Event object
     * @throws DateTimeParseBotException if timeFrom and timeTo argument is not formatted correctly
     * @throws IncompleteBotException if remainder information does not contain datetime
     */
    public EventCommand(TaskList taskList, String remainderInfo)
            throws DateTimeParseBotException, IncompleteBotException {
        if (remainderInfo.isBlank()) {
            throw new IncompleteBotException("OOPS!!! The description of an event cannot be empty.");
        }
        String[] parsedAtFrom = InputParser.getSplitAtFrom(remainderInfo);
        if (parsedAtFrom.length == 1) {
            throw new IncompleteBotException("OOPS!!! The starting timing of an event cannot be empty.");
        }
        String taskDetail = parsedAtFrom[0];
        String[] parsedAtTo = InputParser.getSplitAtTo(parsedAtFrom[1]);
        if (parsedAtTo.length == 1) {
            throw new IncompleteBotException("OOPS!!! The ending timing of an event cannot be empty.");
        }
        this.taskList = taskList;
        this.event = new Event(taskDetail, parsedAtTo[0], parsedAtTo[1]);
    }

    /**
     * Executes a series of instructions specific to creating adding an Event object
     * and returns the execution output
     *
     * @return String of the outcome of the command execution
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     */
    @Override
    public String execute() throws FileErrorBotException {
        this.taskList.add(this.event);
        Storage.save(this.taskList);
        return this.toString();
    }

    /**
     * Returns a String representation of EventCommand object
     *
     * @return String representation of EventCommand object
     */
    @Override
    public String toString() {
        if (this.taskList.length() <= 1) {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.event + "\n" +
                    "Now you have " + this.taskList.length() + " task in the list.\n" +
                    Command.SPACER;
        } else {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.event + "\n" +
                    "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                    Command.SPACER;
        }
    }
}


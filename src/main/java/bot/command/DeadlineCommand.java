package bot.command;

import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.exception.IncompleteBotException;

import bot.parsers.InputParser;
import bot.task.TaskList;
import bot.task.Deadline;
import bot.storage.Storage;

public class DeadlineCommand extends Command {

    private final TaskList taskList;
    private final Deadline deadline;

    /**
     * Creates an instance of DeadlineCommand object
     *
     * @param taskList the list of tasks
     * @param remainderInfo the uncleaned information pertaining to instancing a Deadline object
     * @throws DateTimeParseBotException if dueDate argument is not formatted correctly
     * @throws IncompleteBotException if remainder information does not contain a datetime
     */
    public DeadlineCommand(TaskList taskList, String remainderInfo)
            throws DateTimeParseBotException, IncompleteBotException {
        if (remainderInfo.isBlank()) {
            throw new IncompleteBotException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] parsedAtBy = InputParser.getSplitAtBy(remainderInfo);
        if (parsedAtBy.length == 1) {
            throw new IncompleteBotException("OOPS!!! The timing of a deadline cannot be empty.");
        }
        this.taskList = taskList;
        this.deadline = new Deadline(InputParser.getLeftOfSplit(parsedAtBy),
                InputParser.getRightOfSplit(parsedAtBy));

    }

    /**
     * Executes a series of instructions specific to creating a Deadline object and returns
     * the execution output
     *
     * @return String of the outcome of the command execution
     * @throws FileErrorBotException if the file or directory is missing or corrupted
     */
    public String execute() throws FileErrorBotException {
        this.taskList.add(this.deadline);
        Storage.save(this.taskList);
        return this.toString();
    }

    /**
     * Returns a String representation of DeadlineCommand object
     *
     * @return String representation of DeadlineCommand object
     */
    @Override
    public String toString() {
        if (this.taskList.length() <= 1) {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.deadline + "\n" +
                    "Now you have " + this.taskList.length() + " task in the list.\n" +
                    Command.SPACER;
        } else {
            return Command.SPACER + "\n" +
                    "Got it. I've added this task:\n" +
                    this.deadline + "\n" +
                    "Now you have " + this.taskList.length() + " tasks in the list.\n" +
                    Command.SPACER;
        }
    }
}

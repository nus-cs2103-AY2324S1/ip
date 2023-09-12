package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;

/**
 * Command to view user statistics of task completion.
 */
public class ViewStatisticsCommand extends Command {
    /**
     * Base constructor for a type of command.
     *
     * @param taskList  {@code TaskList} with available tasks to operate on.
     * @param parseInfo parsed information from {@code Parser}.
     */
    public ViewStatisticsCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    @Override
    public String[] execute() throws CommandError {
        if (this.taskList.size() == 0) {
            throw new CommandError("You do not have any tasks to view the statistics of!");
        }

        return new String[]{
            "Pulling up your latest statistics!",
        };
    }
}

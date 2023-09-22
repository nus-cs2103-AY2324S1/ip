package cyrus.commands;

import java.util.ArrayList;
import java.util.List;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;

/**
 * Command to find {@code Task} given keyword.
 */
public class FindTaskCommand extends Command {
    public FindTaskCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    /**
     * Returns list of matching tasks given keyword, taken from argument.
     */
    @Override
    public String[] execute() throws CommandError {
        if (this.parseInfo.hasNoArgument()) {
            throw new CommandError("Missing search term");
        }

        var tasks = this.taskList.findTask(this.parseInfo.getArgument());
        if (tasks.size() == 0) {
            return new String[]{
                "No tasks found."
            };
        }
        List<String> lines = new ArrayList<>();
        lines.add("Here are the matching tasks in your list:");
        for (var task : tasks) {
            lines.add(task.toString());
        }
        return lines.toArray(String[]::new);
    }
}

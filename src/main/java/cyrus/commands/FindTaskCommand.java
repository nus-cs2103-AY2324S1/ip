package cyrus.commands;

import java.util.ArrayList;
import java.util.List;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;

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
    public void execute() {
        var tasks = this.taskList.findTask(this.parseInfo.getArgument());
        if (tasks.size() == 0) {
            Ui.printText("No tasks found");
            return;
        }
        List<String> lines = new ArrayList<>();
        lines.add("Here are the matching tasks in your list:");
        for (var task : tasks) {
            lines.add(task.toString());
        }
        Ui.printText(lines.toArray(String[]::new));
    }
}

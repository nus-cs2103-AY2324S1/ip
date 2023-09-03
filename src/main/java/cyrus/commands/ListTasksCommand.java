package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.ui.CliUi;

/**
 * Command to list out the {@code Task} within the {@code TaskList}.
 */
public class ListTasksCommand extends Command {
    public ListTasksCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    /**
     * Print the string representation of the {@code TaskList}.
     */
    @Override
    public void execute() {
        CliUi.printText(this.taskList.toString());
    }
}

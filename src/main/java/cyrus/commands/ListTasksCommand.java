package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;

public class ListTasksCommand extends Command {
    public ListTasksCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    @Override
    public void execute() {
        Ui.printText(this.taskList.toString());
    }
}

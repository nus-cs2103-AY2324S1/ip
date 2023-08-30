package taskmate.commands;

import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;

public class ListCommand extends Command {

    String commandType;
    boolean isExit;

    public ListCommand() {
        this.commandType = "List";
        this.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printAllTasks(tasks);
    }
}

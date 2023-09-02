package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    public void execute(TaskList tasks, Ui ui) {
        ui.listTasks(tasks);
    }
}

package arona.commands;

import arona.task.TaskList;
import arona.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute() {
        ui.showTaskList(taskList);
    }
}

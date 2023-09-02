package commands;

import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    public ListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute() {
        ui.showTaskList(taskList);
    }
}

package Jelly.commands;

import Jelly.main.Storage;
import Jelly.main.TaskList;
import Jelly.main.Ui;
import Jelly.task.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (index <= 0 || index > 100 || index > taskList.size()) {
            return ("Invalid input");
        } else if (taskList.get(index - 1) != null) {
            Task t = taskList.get(index - 1);
            taskList.delete(index - 1);
            return ui.deleteMessage(t, taskList.size());
        } else {
            return ("Invalid input");
        }
    }
}

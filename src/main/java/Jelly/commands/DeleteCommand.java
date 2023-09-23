package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;
import jelly.task.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert index > 0 : "Index should be positive.";
        if (index <= 0 || index > 100 || index > taskList.size()) {
            return ("Invalid input");
        } else if (taskList.get(index - 1) != null) {
            Task t = taskList.get(index - 1);
            taskList.delete(index - 1);
            storage.saveAndExit(taskList);
            return ui.deleteMessage(t, taskList.size());
        } else {
            return ("Invalid input");
        }
    }
}

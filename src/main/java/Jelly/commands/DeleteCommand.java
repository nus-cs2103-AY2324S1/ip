package Jelly.commands;

import Jelly.exceptions.JellyBlankMessageException;
import Jelly.main.Storage;
import Jelly.main.TaskList;
import Jelly.main.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (index <= 0 || index > 100 || index > taskList.size()) {
            System.out.println("Invalid input");
        } else if (taskList.get(index - 1) != null) {
            ui.deleteMessage(taskList.get(index - 1), taskList.size() - 1);
            taskList.delete(index - 1);
        } else {
            System.out.println("Invalid input");
        }
    }
}

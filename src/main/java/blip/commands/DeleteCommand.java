package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;
import blip.exceptions.*;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        // Task number does not exist.
        try {
            if (this.index < 0 || this.index >= taskList.size()) {
                throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
            }
            Task taskToDelete = taskList.getTask(index);
            taskList.deleteTask(index);
            storage.saveToFile(taskList);
            ui.deletesTasksMsg(taskToDelete, taskList.size());
        } catch (WrongNumberException e) {
            ui.showInvalidTaskNumErr();
        }
    }
}

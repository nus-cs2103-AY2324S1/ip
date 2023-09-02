package Command;

import Task.TaskList;
import Main.UI;
import Main.Storage;

import Exception.DukeException;

public class DeleteCommand extends Command {
    String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.deleteTask(fullCommand);
        storage.saveList(taskList);
    }

}

package Command;

import Task.TaskList;
import Main.UI;
import Main.Storage;

import Exception.DukeException;

public class EventCommand extends Command {
    String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.addEvent(fullCommand);
        storage.saveList(taskList);
    }
}

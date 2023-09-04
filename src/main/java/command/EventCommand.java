package command;

import task.TaskList;
import main.UI;
import main.Storage;

import exception.DukeException;

public class EventCommand extends Command {
    private String fullCommand;


    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.addEvent(fullCommand);
        storage.saveList(taskList);
    }
}

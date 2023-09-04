package command;

import task.TaskList;
import main.UI;
import main.Storage;

import exception.DukeException;

public class ToDoCommand extends Command {
    String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.addToDo(description);
        storage.saveList(taskList);
    }
}

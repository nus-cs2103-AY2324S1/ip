package command;

import task.TaskList;
import main.UI;
import main.Storage;

import exception.DukeException;

/**
 * ToDoCommand is a subclass of Command.
 * Used to execute ToDo Commands.
 */
public class ToDoCommand extends Command {
    String description;

    /**
     * Constructor for ToDo Command
     * @param description user description to be saved for ToDo tasks
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.addToDo(description);
        storage.saveList(taskList);
    }
}

package Command;

import Exception.DukeException;
import Main.Storage;
import Main.UI;
import Task.TaskList;

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

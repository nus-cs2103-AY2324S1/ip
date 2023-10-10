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

    /**
     *
     * @param taskList object containing the ArrayList of tasks
     * @param ui user interface responsible for terminal messages
     * @param storage object to communicate with data.txt file
     * @return todo message to be printed in the GUI
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String message = taskList.addToDo(description);
        storage.saveList(taskList);
        return message;
    }
}

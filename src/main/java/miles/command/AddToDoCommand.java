package miles.command;

import miles.Storage;
import miles.TaskList;
import miles.Ui;
import miles.task.ToDo;

/**
 * Represents adding a todo task command.
 */
public class AddToDoCommand extends Command {
    private String input;

    /**
     * Constructor to create a new todo command.
     * @param input user input
     */
    public AddToDoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo newToDo = new ToDo(input);
        storage.saveWhenAddTask(newToDo, taskList);
        int n = taskList.getSize();
        ui.printAddedTask(newToDo, n);
    }
}

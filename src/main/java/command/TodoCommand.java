package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.EmptyInputException;
import task.Todo;

/**
 * The Command to indicate that the user wishes to add a todo into the task list.
 */
public class TodoCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of TodoCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public TodoCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length > 1) {
            String description = input.split(" ", 2)[1];
            Todo todo = new Todo(description);
            String str = ui.printAddTask(taskList, todo);
            storage.writeTasks(taskList);
            return str;
        } else {
            throw new EmptyInputException("a todo");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

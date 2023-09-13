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

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length > 1) {
            String description = input.split(" ", 2)[1];
            Todo todo = new Todo(description);
            taskList.addTask(todo);
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

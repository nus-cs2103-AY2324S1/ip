package command;

import duke.Ui;
import duke.TaskList;

import exception.EmptyInputException;

import task.Todo;

/**
 * The Command to indicate that the user wishes to add a todo into the task list.
 */
public class TodoCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of TodoCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public TodoCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length > 1) {
            String description = input.split(" ", 2)[1];
            Todo t = new Todo(description);
            taskList.addTask(t);
        } else {
            throw new EmptyInputException("a todo");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

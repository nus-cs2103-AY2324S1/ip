package command;

import duke.Ui;
import duke.TaskList;

import exception.EmptyInputException;

import task.Todo;

public class TodoCommand extends Command {
    private TaskList taskList;
    private Ui ui;
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
            throw new EmptyInputException("todo");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

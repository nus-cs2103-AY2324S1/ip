package command;

import exception.DialogixException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Todo;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        tasks.addToStack();
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.printAddSuccessMessage(todo, tasks.size());
        storage.save(tasks.getAllTasks());
    }

}

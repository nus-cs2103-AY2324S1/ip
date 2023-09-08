package buddy.commands;

import buddy.TaskList;
import buddy.Todo;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        Todo todo = new Todo(description, false);
        tasks.addTask(todo);
        ui.printAddSuccessMessage(todo, tasks);
        storage.writeToFile(tasks.getAllTasks());
    }
}

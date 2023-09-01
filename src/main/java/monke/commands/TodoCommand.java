package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.tasks.Todo;
import monke.Ui;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Todo todo = new Todo(this.description);
        tasks.add(todo);
        storage.saveData(tasks);

        ui.showAddTask(todo, tasks.size());
    }
}

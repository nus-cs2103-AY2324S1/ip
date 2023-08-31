package commands;

import data.TaskList;
import data.exception.DukeException;
import data.tasks.Task;
import data.tasks.Todo;
import storage.Storage;
import ui.Ui;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task todo = new Todo(description);
        tasks.add(todo);
        storage.update(tasks);
        ui.displayMsg(new String[] {
            "Okie! I've added a new " + Ui.cTxt("TODO", Ui.COLOR.GREEN) + ":",
            "  " + todo.toString(),
            "Total no. of tasks stored: " + tasks.getSize()
        });
    }
}

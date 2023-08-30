package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Tasks.ToDos;
import Duke.Ui;

import java.io.IOException;

public class TodoCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected String input;
    public TodoCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    @Override
    public void execute() throws DukeException {
        ToDos task = new ToDos(input);
        taskList.addTask(task);
        ui.printAddTask(taskList, task);

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }
}

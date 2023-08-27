package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

public class AddToDoCommand extends Command {
    private String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        ui.printAddSuccessMessage(todo, tasks.getAllTasks());
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.ToDo;

public class AddToDoCommand extends Command {
    private String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        ui.addToListSuccess(todo, taskList.size());
        storage.saveList(taskList.getAllTasks());
    }
}

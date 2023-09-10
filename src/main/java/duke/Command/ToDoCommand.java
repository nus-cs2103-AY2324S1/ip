package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;



public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        Task task = new ToDo(description);
        tasks.addTask(task);
        storage.writeTasksToFile(tasks);
        return ui.printAddTaskToList(tasks, task);
    }
    @Override
    public boolean isExit() {
        return false;
    }

}

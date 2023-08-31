package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import duke.exception.DukeException;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private String description;
    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        ui.displayCompletionMessage(todo, taskList.size());
        storage.saveTasksToFile(taskList);
    }

}

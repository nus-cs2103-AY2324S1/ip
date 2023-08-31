package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import duke.exception.DukeException;
import duke.task.Todo;

public class TodoCommand extends Command {
    private String description;
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(description);
        taskList.add(todo);
        ui.displayCompletionMessage(todo, taskList.size());
        storage.saveTasksToFile(taskList);
    }

}

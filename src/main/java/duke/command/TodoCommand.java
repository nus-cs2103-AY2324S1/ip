package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task temp = new Todo(description);
        taskList.addTask(temp);
        ui.printAddTaskMessage(temp, taskList);
        storage.save(taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof UnmarkCommand) {
            TodoCommand temp = (TodoCommand)  obj;
            if (temp.description.equals(this.description)) {
                return true;
            }
        }
        return false;
    }
}

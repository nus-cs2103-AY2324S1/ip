package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;
import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        Task temp = new Todo(description);
        taskList.addTask(temp);
        storage.save(taskList);
        return uiManager.getAddTaskMessage(temp, taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof TodoCommand) {
            TodoCommand temp = (TodoCommand)  obj;
            if (temp.description.equals(this.description)) {
                return true;
            }
        }
        return false;
    }
}

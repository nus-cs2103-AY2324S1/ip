package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String description;
    public TodoCommand(String description) {
        this.description = description;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task todo = new Task(this.description);
            tasks.addTask(todo);
            ui.formatTaskResponse(todo, tasks);
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    public boolean isExit() {
        return false;
    };
}

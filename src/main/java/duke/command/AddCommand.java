package duke.command;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.storage.Storage;
import duke.DukeException;

public class AddCommand extends Command {
    private final String type;
    private final String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description= description;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task newTask = list.addTask(this.type, this.description);
        ui.showTaskAdded(newTask, list.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

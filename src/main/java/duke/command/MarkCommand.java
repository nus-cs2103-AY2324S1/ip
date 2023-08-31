package duke.command;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.storage.Storage;
import duke.DukeException;
public class MarkCommand extends Command {
    private final String type;
    private final String pos;

    public MarkCommand(String type, String pos) {
        this.type = type;
        this.pos = pos;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task markedTask = list.markTask(this.type, this.pos);
        ui.showMark(this.type, markedTask);
    }
}

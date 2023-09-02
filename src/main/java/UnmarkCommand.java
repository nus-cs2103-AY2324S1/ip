import java.io.IOException;

public class UnmarkCommand extends Command {

    int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        taskList.unmark(this.index);
    }
}

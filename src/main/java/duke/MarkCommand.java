package duke;
public class MarkCommand extends Command{

    private final int INDEX;
    public MarkCommand(int i) {
        INDEX = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(INDEX);
        ui.showMarked(tasks.get(INDEX));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

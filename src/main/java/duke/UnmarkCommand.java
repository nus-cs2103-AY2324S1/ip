package duke;

public class UnmarkCommand extends Command{
    private final int INDEX;
    public UnmarkCommand(int i) {
        INDEX = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(INDEX);
        ui.showUnmarked(tasks.get(INDEX));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

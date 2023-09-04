public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(index);
            ui.showMarkedTask(tasks.getTask(index));
        } catch (DreException e) {
            ui.showError(e.getMessage());
        }
    }
}
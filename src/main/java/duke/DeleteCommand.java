package duke;
public class DeleteCommand extends Command{

    private final int INDEX;
    public DeleteCommand(int i) {
        INDEX = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.get(INDEX);
        tasks.delete(INDEX);
        ui.showDeleted(t, tasks.total());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

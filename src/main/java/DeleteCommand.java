public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showDeletedTask(tasks.getTask(index));
            tasks.deleteTask(index);
        } catch (DreException e) {
            ui.showError(e.getMessage());
        }
    }
}
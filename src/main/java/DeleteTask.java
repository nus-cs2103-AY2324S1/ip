public class DeleteTask extends Command{
    private int indexToDelete;

    public DeleteTask(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.remove(this.indexToDelete);
        ui.deletedMessage(this.indexToDelete, tasks);
        try {
            storage.saveTasks(tasks);
        } catch (InvalidInputException e) {
            ui.printException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

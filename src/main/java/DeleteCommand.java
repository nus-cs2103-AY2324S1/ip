public class DeleteCommand extends Command {
    static final String COMMAND_WORD = "delete";
    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        Task deleted = tasks.getList().get(taskNumber - 1);
        tasks.delete(taskNumber);
        storage.writeToFile(tasks);
        ui.showDelete(deleted, tasks.getList().size());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

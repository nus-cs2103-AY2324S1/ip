public class UnmarkCommand extends Command {
    static final String COMMAND_WORD = "unmark";
    private int taskNumber;
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        Task unmark = tasks.getList().get(taskNumber - 1);
        tasks.unmark(taskNumber);
        storage.writeToFile(tasks);
        ui.showUnmark(unmark);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

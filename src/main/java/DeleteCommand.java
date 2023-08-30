public class DeleteCommand extends Command{
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(index);
        ui.showMessage("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.getTotalTask() +" tasks in the list.");
    };

    @Override
    public boolean isExit() {
        return false;
    };
}

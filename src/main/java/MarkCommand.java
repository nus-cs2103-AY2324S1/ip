public class MarkCommand implements Command {

    private int id;
    private boolean loading;

    public MarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(id);
        ui.showMessage("Nice! I've marked this task as done: \n" + tasks.getTask(id));
        tasks.updateTasks(storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void setLoading() {
        this.loading = true;
    }

}

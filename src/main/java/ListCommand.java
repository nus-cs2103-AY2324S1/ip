public class ListCommand implements Command {

    private boolean loading;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list: ");
        tasks.displayTasks(ui);
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

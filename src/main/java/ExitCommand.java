public class ExitCommand implements Command {

    private boolean loading;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void setLoading() {
        this.loading = true;
    }

}

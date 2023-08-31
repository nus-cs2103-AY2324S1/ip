public class InvalidCommand implements Command {

    private boolean loading;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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

public class DeleteCommand implements Command {

    private int id;
    private boolean loading;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(id);
        tasks.deleteTask(id);
        ui.showMessage("Noted. I've removed this task: \n" + task);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
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

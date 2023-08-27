public class ListCommand implements Command{
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception{
        ui.showMessage(tasks.toString() + "\n");
        return tasks;
    }
}

public class ClearCommand implements Command{
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception{
        ui.showMessage("Task list cleared.\n");
        tasks = new TaskList();
        storage.save(tasks);
        return tasks;
    }
}
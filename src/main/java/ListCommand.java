public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showTasks(tasks);
    }
}
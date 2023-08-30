public class ListCommand extends Command {
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showAllTasks(tasks.tasks);
    }
}

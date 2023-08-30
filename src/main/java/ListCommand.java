public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showAllTask();
    };

    @Override
    public boolean isExit() {
        return false;
    };
}

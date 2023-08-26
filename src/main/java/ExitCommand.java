public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

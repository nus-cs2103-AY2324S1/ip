public class ExitCommand implements Command{
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception{
        ui.showGoodbye();
        return tasks;
    }
}
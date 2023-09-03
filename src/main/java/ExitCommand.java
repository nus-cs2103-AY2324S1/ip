public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

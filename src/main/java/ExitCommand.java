public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.leave();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}

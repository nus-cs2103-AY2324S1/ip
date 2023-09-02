public class ByeCommand extends Command {
    public ByeCommand() {
        // empty
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        // do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

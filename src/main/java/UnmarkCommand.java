public class UnmarkCommand extends Command {

    private int position;

    public UnmarkCommand(int position) {
        this.position = position;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.unmarkTask(position);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

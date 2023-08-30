package sana;
public class UnmarkCommand extends Command {
    public UnmarkCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {
        tasks.unmark(Integer.parseInt(arguments));
        tasks.update(storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

public class MarkCommand extends Command {
    public MarkCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {
        tasks.mark(Integer.parseInt(arguments));
        tasks.update(storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

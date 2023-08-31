public class ExitCommand extends Command {
    static final String COMMAND_WORD = "bye";
    public ExitCommand() {

    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        storage.writeToFile(tasks);
        ui.exit();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}

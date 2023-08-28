public class ExitCommand extends Command {
    public ExitCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskManager taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
        this.setExit();
    }
}

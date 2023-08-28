public class ExitCommand extends Command {
    public ExitCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
        this.setExit();
    }
}

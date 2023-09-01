public class EchoCommand extends Command{
    private String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showEcho(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

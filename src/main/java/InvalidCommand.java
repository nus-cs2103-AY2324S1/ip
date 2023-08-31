public class InvalidCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printNotSureMessage();
    }
}

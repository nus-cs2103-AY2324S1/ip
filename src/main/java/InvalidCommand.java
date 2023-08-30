public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    public void execute(TaskList tasks, Ui ui) {
        ui.invalidCommandMessage();
    }
}

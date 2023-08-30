public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }

    public void execute(TaskList tasks, Ui ui) {
        ui.endMessage();
    }
}

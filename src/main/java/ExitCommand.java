
public class ExitCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bidFarewell();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}

import java.util.ArrayList;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    public void execute(ArrayList<Task> tasks, Ui ui) {
        ui.invalidCommandMessage();
    }
}

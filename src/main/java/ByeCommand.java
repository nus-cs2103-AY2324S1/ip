import java.util.ArrayList;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(CommandType.BYE);
    }

    public void execute(ArrayList<Task> tasks, Ui ui) {
        ui.endMessage();
    }
}

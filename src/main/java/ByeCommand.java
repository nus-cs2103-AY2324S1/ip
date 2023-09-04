import java.util.ArrayList;

public class ByeCommand extends Command {

    public ByeCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }

}

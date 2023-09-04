import java.util.ArrayList;

public abstract class Command {
    protected ArrayList<String> commandDetails;

    public Command(ArrayList<String> commandDetails) {
        this.commandDetails = commandDetails;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}

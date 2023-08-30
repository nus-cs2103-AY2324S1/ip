import java.util.ArrayList;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage);
}

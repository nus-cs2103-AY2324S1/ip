import java.io.IOException;

public abstract class Command {
    boolean isExit;
    String input;

    public boolean isExit() {
        return isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}

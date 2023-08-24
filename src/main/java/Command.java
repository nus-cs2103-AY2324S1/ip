import java.io.IOException;

public abstract class Command {

    private boolean isExit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MinionException, IOException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}

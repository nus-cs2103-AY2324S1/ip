import java.io.IOException;

public abstract class Command {
    private boolean isExit;

    protected Command(Boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws QiException;

    public boolean isExit() {
        return this.isExit;
    }
}

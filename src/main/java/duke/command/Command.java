import java.io.IOException;

public abstract class Command {

    boolean isExit;
    abstract void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;

    public Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return false;
    }

}

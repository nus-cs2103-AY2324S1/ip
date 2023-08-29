import exceptions.DukeException;

public abstract class Command {
    abstract void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) throws DukeException;

    public boolean isExit() {
        return false;
    }
}

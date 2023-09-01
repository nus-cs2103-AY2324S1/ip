
public abstract class Command {

    protected boolean isExit = false;
    public boolean isExit() {
        return isExit;
    }
    public abstract void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException;
}

public abstract class Command {

    private boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NoacException;

    public boolean isExit() {
        return isExit;
    }
}

public abstract class Command {
    boolean isExit;
    abstract protected void execute(TaskList tasks, Ui ui, Storage storage) throws BongoException;

    public Command() {
        this.isExit = false;
    }

    protected boolean isExit() {
        return false;
    }
}

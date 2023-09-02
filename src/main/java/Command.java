public abstract class Command {
    boolean isExit;
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws BareumException;
    public boolean isExit() {
        return isExit;
    }
}

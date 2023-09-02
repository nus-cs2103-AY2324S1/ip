public abstract class Command {
    public Command() {
        // empty
    }

    public abstract void execute(Ui ui, TaskList taskList) throws CrusaderException;

    public boolean isExit() {
        return false;
    }
}

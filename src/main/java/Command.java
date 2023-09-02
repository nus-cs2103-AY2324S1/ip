public abstract class Command {
    public Command() {
        // empty
    }

    public abstract void execute(Ui ui, TaskList taskList);

    public boolean isExit() {
        return false;
    }
}

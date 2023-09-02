public abstract class Command {
    public abstract void execute(Ui ui, TaskList tasks);

    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}

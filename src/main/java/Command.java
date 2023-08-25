public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DidierException;
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}

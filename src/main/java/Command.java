public abstract class Command {
    abstract protected void execute(TaskList tasks, Ui ui, Storage storage);
}

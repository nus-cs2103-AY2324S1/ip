public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage store);
    public abstract boolean isExit();

}

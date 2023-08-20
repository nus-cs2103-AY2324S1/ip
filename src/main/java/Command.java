public abstract class Command {
    public abstract void execute(TaskList tasks,Ui ui);
    public abstract boolean isExit();

}


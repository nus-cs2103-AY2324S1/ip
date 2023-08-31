public abstract class Command {
    protected String fullCommand;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    public abstract boolean isExit();
    public abstract void execute(TaskList tasks ,Ui ui, Storage storage);
}

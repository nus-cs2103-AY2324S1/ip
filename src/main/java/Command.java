public abstract class Command {

    boolean isExit;
    String commandType;

    String getType() {
        return this.commandType;
    };
    boolean isExit() {
        return this.isExit;
    };
    abstract void execute(TaskList tasks, Ui ui, Storage storage);

    @Override
    public String toString() {
        return this.getType();
    }
}

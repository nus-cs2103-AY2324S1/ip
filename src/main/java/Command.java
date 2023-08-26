public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException;

    public abstract boolean isExit();

}

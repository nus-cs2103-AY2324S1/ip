public abstract class Command {
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws DukeException;
}

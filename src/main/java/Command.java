public abstract class Command {

    protected boolean exitsNext = false;

    public abstract void execute(TaskList items, Ui ui, Storage storage) throws DukeException;

    public boolean exitsNext() {
        return exitsNext;
    }
}

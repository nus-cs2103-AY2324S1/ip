public class Command {
    protected boolean isExit = false;

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("OOPS!!! I don't know what that means :-(");
    }

    public boolean isExit() {
        return this.isExit;
    }
}

abstract class Command {
    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    protected void setExit(boolean exit) {
        isExit = exit;
    }

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}

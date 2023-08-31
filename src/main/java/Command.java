public class Command {
    public Command() {

    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        throw new MaxException("This command cannot be executed bro.");
    }
    public boolean isExit() {
        return false;
    }
}

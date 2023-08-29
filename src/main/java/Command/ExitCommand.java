public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public ExitCommand(int index) {
        super(index);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ui.print("Bye. Hope to see you again soon lol!");
    }
}

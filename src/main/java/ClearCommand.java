public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        ui.showMessage("Got it. I've cleared all tasks.");
    }
}

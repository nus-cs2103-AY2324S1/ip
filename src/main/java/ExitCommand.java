public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks);
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}

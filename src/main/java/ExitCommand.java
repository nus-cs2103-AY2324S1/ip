public class ExitCommand extends Command {

    public ExitCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        store.saveTasks(tasks.taskList);
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

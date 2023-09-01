public class ListCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTaskResponse(tasks);
    };
    public boolean isExit() {
        return false;
    };
}

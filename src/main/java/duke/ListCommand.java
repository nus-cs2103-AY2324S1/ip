package duke;
public class ListCommand extends Command{

    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList();
        for (int i = 1; i <= tasks.total(); i++) {
            Task t = tasks.get(i - 1);
            ui.showTask(t, i);
        }
       ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

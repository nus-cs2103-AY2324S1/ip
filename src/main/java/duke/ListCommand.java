package duke;

/**
 * Lists all the tasks in task list
 */
public class ListCommand extends Command{

    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showTaskList(tasks);
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

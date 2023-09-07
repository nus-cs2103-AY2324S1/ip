package duke;

/**
 * Lists all the tasks in task list
 */
public class ListCommand extends Command{

    public ListCommand() {}

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

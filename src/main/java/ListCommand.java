public class ListCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    public ListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.listTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
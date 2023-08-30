public class UnmarkCommand extends Command {
    private int taskNum;

    public UnmarkCommand(int taskNum) {
        super(CommandType.UNMARK);
    }

    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = tasks.getTask(taskNum - 1);
            task.changeStatus(false);
            ui.unmarkTaskMessage(task);
            ui.taskListSizeMessage(tasks.getSize(), true);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You have no such task, mortal.");
            return;
        }
    }
}

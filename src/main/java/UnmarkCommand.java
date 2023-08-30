import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private int taskNum;

    public UnmarkCommand(int taskNum) {
        super(CommandType.UNMARK);
    }

    public void execute(ArrayList<Task> tasks, Ui ui) {
        try {
            Task task = tasks.get(taskNum - 1);
            task.changeStatus(false);
            ui.unmarkTaskMessage(task);
            ui.taskListSizeMessage(tasks.size(), true);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You have no such task, mortal.");
            return;
        }
    }
}

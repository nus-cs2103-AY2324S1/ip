import java.util.ArrayList;

public class DeleteCommand extends Command {
    int taskNum;

    public DeleteCommand(int taskNum) {
        super(CommandType.DELETE);
        this.taskNum = taskNum;
    }

    public void execute(ArrayList<Task> tasks, Ui ui) {
        try {
            Task task = tasks.get(taskNum - 1);
            tasks.remove(taskNum - 1);
            ui.deleteMessage(task);
            ui.taskListSizeMessage(tasks.size(), false);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("You have no such task, mortal.");
            return;
        }
    }
}

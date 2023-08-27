import java.util.ArrayList;

public class DeleteCommand implements Command{
    private final int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception{
        ui.showMessage(" Noted. I've removed this task:\n");
        ui.showMessage("    " + tasks.getTasks().get(index - 1) + "\n");
        TaskList newTasks = tasks.delete(index);
        ui.showMessage("Now you have " + Integer.toString(tasks.getTasks().size()) +
                " tasks in the list.\n");
        return newTasks;
    }
}
import java.util.ArrayList;

public class MarkCommand implements Command{
    private final Boolean marked;
    private final int index;

    MarkCommand(int index, Boolean marked) {
        this.index = index;
        this.marked = marked;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception{
        if (marked) {
            ui.showMessage("Nice! I've marked this task as done:\n");
        } else {
            ui.showMessage("OK, I've marked this task as not done yet:\n");
        }
        TaskList newTasks = marked ? tasks.mark(index) : tasks.unmark(index);
        ui.showMessage("    " + tasks.getTasks().get(index - 1));;
        return newTasks;
    }
}

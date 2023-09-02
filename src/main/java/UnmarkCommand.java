import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    public UnmarkCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String input = ui.getInput();
        String indexStr = input.split(" ")[1];
        int num = Integer.valueOf(indexStr);
        taskList.unmarkTask(num);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package command;

import duke.Ui;
import duke.TaskList;

public class DeleteCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    public DeleteCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        String input = ui.getInput();
        String indexStr = input.split(" ")[1];
        int num = Integer.valueOf(indexStr);
        taskList.deleteTask(num);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
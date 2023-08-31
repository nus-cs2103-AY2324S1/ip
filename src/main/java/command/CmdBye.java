package command;

import task.TaskList;
import ui.Ui;

public class CmdBye extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.print("Bye meow! Hope to see you again soon!");
    }

}

package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ExitCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.print("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}

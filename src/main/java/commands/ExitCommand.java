package commands;

import functional.TaskList;
import functional.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, boolean marked, boolean load) {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
        super.isExit = true;
    }
}

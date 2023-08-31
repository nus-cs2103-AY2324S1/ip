package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        System.out.println("Bye! Have a nice day!");
    }
}

package Remy.command;

import Remy.task.TaskList;
import Remy.Ui;
import Remy.Storage;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    @Override
    public boolean isExit() {
        return true;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.printExitMessage();
    }
}

package taskmate.commands;

import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;

public class HelpCommand extends Command {

    String commandType;
    boolean isExit;

    public HelpCommand() {
        this.commandType = "Help";
        this.isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInputSpecifications(storage.getAbsoluteSaveFilePath());
    }
}

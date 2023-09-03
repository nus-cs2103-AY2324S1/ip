package taskmate.commands;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    String commandType;

    public ExitCommand() {
        this.commandType = "Exit";
        this.isExit = true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Exit procedure
        // 1. Write incomplete tasks to disk
        String saveTaskText = tasks.formatAllTasksForSaving();
        try {
            storage.writeToFile(saveTaskText);
        } catch (IOException e) {
            ui.printSaveFailResponse(System.getProperty("user.dir") +
                    storage.getSaveFilePath().substring(1).replace("/", "\\"));
        }

        // 2. Print exit message
        ui.farewellUser();
    }

}

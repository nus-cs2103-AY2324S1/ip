import java.io.IOException;

public class ExitCommand extends Command {

    String commandType;
    boolean isExit;

    ExitCommand() {
        this.commandType = "Exit";
        this.isExit = true;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        // Exit procedure
        // 1. Write incomplete tasks to disk
        String saveTaskText = tasks.formatAllTasksForSaving();
        System.out.println(saveTaskText);
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

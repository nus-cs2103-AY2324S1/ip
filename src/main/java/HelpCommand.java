public class HelpCommand extends Command {

    String commandType;
    boolean isExit;

    HelpCommand() {
        this.commandType = "Help";
        this.isExit = false;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printInputSpecifications(storage.getAbsoluteSaveFilePath());
    }
}

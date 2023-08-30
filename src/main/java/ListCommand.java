import java.io.IOException;

public class ListCommand extends Command {

    String commandType;
    boolean isExit;

    ListCommand() {
        this.commandType = "List";
        this.isExit = false;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printAllTasks(tasks);
    }
}

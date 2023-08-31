package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
public class InvalidCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printNotSureMessage();
    }
}

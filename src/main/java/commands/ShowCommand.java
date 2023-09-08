package commands;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public class ShowCommand extends Command {
    public ShowCommand() {
        super();
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        list.printList();
    }
}

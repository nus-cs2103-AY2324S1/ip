import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}

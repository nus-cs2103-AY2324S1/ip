import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    public void execute(ArrayList<Task> tasks, Ui ui) {
        ui.listTasks(tasks);
    }
}

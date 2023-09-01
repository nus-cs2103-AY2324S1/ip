package Command;
import Duke.TaskList;
import Duke.UserInterface;

public class ListCommand implements Commandable {
    public boolean execute(TaskList list, UserInterface ui) {
        ui.output(list.listString());
        return false;
    }
}
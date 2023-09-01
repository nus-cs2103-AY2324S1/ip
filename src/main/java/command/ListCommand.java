package command;
import duke.TaskList;
import duke.UserInterface;

public class ListCommand implements Commandable {
    public boolean execute(TaskList list, UserInterface ui) {
        ui.output(list.listString());
        return false;
    }
}

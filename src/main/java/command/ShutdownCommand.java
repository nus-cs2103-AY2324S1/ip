package command;
import duke.TaskList;
import duke.UserInterface;

public class ShutdownCommand implements Commandable {
    public boolean execute(TaskList list, UserInterface ui) {
        ui.output("Shutting down now!");
        return true;
    }
}

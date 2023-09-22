package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

public class UnMarkCommand extends Command{

    private final String[] command;

    public UnMarkCommand(String[] command) {
        this.command = command;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) {
        assert command != null : "Should have an input!";
        int taskNum = Integer.parseInt(this.command[1]);
        String returnString = tasks.unMark(taskNum - 1);
        storage.writeToFile(tasks.getTaskAsList());
        return returnString;
    }
}

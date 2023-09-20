package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

public class ListCommand extends Command {

    private String command;

    public ListCommand(String command) {
        super(command);
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) {
        return null;
    }
}

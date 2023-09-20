package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

public abstract class Command {

    private String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract String execute(Tasklist tasks, Storage storage);

}

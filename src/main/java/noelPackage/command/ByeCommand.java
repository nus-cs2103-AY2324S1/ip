package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

public class ByeCommand extends Command{

    static String BYE_MSG = " Bye. Hope to see you again soon!";

    public ByeCommand() {
        super(BYE_MSG);
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) {
        return BYE_MSG;
    }
}

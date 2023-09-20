package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

public class markCommand extends Command {

    private String output;

    public markCommand(Tasklist tasks, int taskNum, Storage storage) {
        super("");
        this.output = tasks.markAsDone(taskNum);
        storage.writeToFile(tasks.getTaskAsList());
    }

    @Override
    public String toString(){
        return output;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) {
        return null;
    }
}

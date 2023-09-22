package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

public class ToDoCommand extends Command {

    private final String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) {
        assert command != null : "Should have an input!";

        String[] result = command.split("todo ");

        if (result.length <= 1) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        } else {
            String returnStr = tasks.addToDo(result[1]);
            storage.writeToFile(tasks.getTaskAsList());
            return returnStr;
        }
        return "";
    }
}

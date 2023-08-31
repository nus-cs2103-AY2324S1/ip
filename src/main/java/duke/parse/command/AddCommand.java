package duke.parse.command;

import duke.Duke;
import duke.task.Task;

public class AddCommand implements Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean execute(Duke bot) {
        bot.addTaskToList(task);
        return true;
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof AddCommand) {
            AddCommand anotherAdd = (AddCommand) another;
            return this.task.equals(anotherAdd.task);
        }
        return false;
    }
}

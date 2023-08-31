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
}

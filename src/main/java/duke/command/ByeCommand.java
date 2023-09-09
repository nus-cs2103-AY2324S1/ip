package duke.command;

import duke.main.Storage;
import duke.task.TaskList;

/**
 * A class for the command for terminating the Duke bot.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return this.printCommand(taskList);
    }

    @Override
    public String printCommand(TaskList taskList) {
        return "JonBird:\n\tBye. Hope to see you again soon!";
    }

    @Override
    public boolean isContinue() {
        return false;
    }
}

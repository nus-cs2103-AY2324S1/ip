package crusader.command;

import crusader.Storage;
import crusader.TaskList;

/**
 * Command used to exit the bot.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        // empty
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        storage.saveTasks(taskList.getTasks());
        return "Bye!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
